package no.geekworld.pages;



import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.wicket.PageParameters;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.markup.html.form.upload.FileUploadField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.file.Folder;
import org.apache.wicket.util.lang.Bytes;

/**
 * Created by IntelliJ IDEA.
 * User: tobiast
 * Date: Sep 28, 2011
 * Time: 9:45:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadPage extends WebPage
{

	/** List of files, model for file table. */
	private List files = new ArrayList();

	/** Reference to listview for easy access. */
	private FileListView fileListView;

	/** Upload folder */
	private Folder uploadFolder;

	/**
	 * Constructor.
	 *
	 * @param parameters
	 *            Page parameters
	 */
	public UploadPage(final PageParameters parameters)
	{

		// Set upload folder to tempdir + 'wicket-uploads'.
		this.uploadFolder = new Folder(System.getProperty("java.io.tmpdir"), "wicket-uploads");

		// Ensure folder exists
		uploadFolder.mkdirs();

		// Create feedback panels
		final FeedbackPanel uploadFeedback = new FeedbackPanel("uploadFeedback");

		// Add uploadFeedback to the page itself
		add(uploadFeedback);

		// Add simple upload form, which is hooked up to its feedback panel by
		// virtue of that panel being nested in the form.
		final FileUploadForm simpleUploadForm = new FileUploadForm("simpleUpload");
		add(simpleUploadForm);

		// Add folder view
		add(new Label("dir", uploadFolder.getAbsolutePath()));
		files.addAll(Arrays.asList(uploadFolder.listFiles()));
		fileListView = new FileListView("fileList", files);
		add(fileListView);

	}

	/**
	 * Refresh file list.
	 */
	private void refreshFiles()
	{
		fileListView.modelChanging();
		files.clear();
		files.addAll(Arrays.asList(uploadFolder.listFiles()));
	}

	/**
	 * Check whether the file allready exists, and if so, try to delete it.
	 *
	 * @param newFile
	 *            the file to check
	 */
	private void checkFileExists(File newFile)
	{
		if (newFile.exists())
		{
			// Try to delete the file
			if (!Files.remove(newFile))
			{
				throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
			}
		}
	}

	/**
	 * Form for uploads.
	 */
	private class FileUploadForm extends Form
	{
		private FileUploadField fileUploadField;

		/**
		 * Construct.
		 *
		 * @param name
		 *            Component name
		 */
		public FileUploadForm(String name)
		{
			super(name);

			// set this form to multipart mode (allways needed for uploads!)
			setMultiPart(true);

			// Add one file input field
			add(fileUploadField = new FileUploadField("fileInput"));

			// Set maximum size to 100K for demo purposes
			setMaxSize(Bytes.kilobytes(100));
		}

		protected void onSubmit()
		{
			final FileUpload upload = fileUploadField.getFileUpload();
			if (upload != null)
			{
				// Create a new file
				File newFile = new File(uploadFolder, upload.getClientFileName());

				// Check new file, delete if it allready existed
				checkFileExists(newFile);
				try
				{
					// Save to new file
					newFile.createNewFile();
					upload.writeTo(newFile);

					UploadPage.this.info("saved file: " + upload.getClientFileName());
				}
				catch (Exception e)
				{
					throw new IllegalStateException("Unable to write file");
				}

				// refresh the file list view
				refreshFiles();
			}
		}
	}

	/**
	 * List view for files in upload folder.
	 */
	private class FileListView extends ListView
	{
		/**
		 * Construct.
		 *
		 * @param name
		 *            Component name
		 * @param files
		 *            The file list model
		 */
		public FileListView(String name, final List files)
		{
			super(name, files);
		}

		/**
		 * @see ListView#populateItem(ListItem)
		 */
		protected void populateItem(ListItem listItem)
		{
			final File file = (File)listItem.getModelObject();
			listItem.add(new Label("file", file.getName()));
			listItem.add(new Link("delete")
			{
				public void onClick()
				{
					Files.remove(file);
					refreshFiles();
					UploadPage.this.info("Deleted " + file);
				}
			});
		}
	}
}

