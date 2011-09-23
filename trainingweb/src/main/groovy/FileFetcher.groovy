

public class FileFetcher{


     public FileFetcher(String url){

     def trainingCenterDatabase = new XmlSlurper().parseText(new URL("http://connect.garmin.com/proxy/activity-service-1.0/tcx/activity/111448305?full=true").text)

        trainingCenterDatabase.Activities.Activity.Lap.Track.Trackpoint.each{

        String time = it.Time
        String heartRate = it.HeartRateBpm.Value

        println("Time: " + time + " HeartRate: " + heartRate)
        println("Zone: " + heartRateZone(heartRate));


      }
   }


    def int heartRateZone(String heartRate){

      def heartRates = [120, 140, 150, 160, 170, 190]

      int zone = 0;
      boolean foundZone = false;
      while (!foundZone) {
         if (Integer.parseInt(heartRate) > heartRates[zone] ){
           zone++;
         }else {
           break;
         }
      }
      return zone;
    }


  public static void main(String[] args) {
     FileFetcher fileFetcher = new FileFetcher("wukka");
   }



}

