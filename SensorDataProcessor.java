import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public RacingStatsCalculator {


    private double[][][] data;
    private double[][] limit;


    // Constructor to initialize data and limit
    public RacingStatsCalculator(double[][][] data, double[][] limit) {
        this.data = data;
        this.limit = limit;
    }


    // Calculate and write racing stats data into a file
    public void calculateRaceStatsData(double RaceStatsData) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("RacingStatsData.txt"))) {   
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    for (int k = 0; k < data[0][0].length; k++) {

                        double dataAvg=average(data[i][j]);
                        double calculatedValue = data[i][j][k] / RaceStatsData - Math.pow(limit[i][j], 2.0);
                        if (dataAvg > 10) {
                            if(dataAvg<50){
                                break;
                                }
                        } 
                        if (Math.max(data[i][j][k], calculatedValue) > data[i][j][k]) {
                            break;    
                        } 
                        if (Math.pow(Math.abs(data[i][j][k]), 3) < Math.pow(Math.abs(calculatedValue), 3)
                                && dataAvg < calculatedValue && (i + 1) * (j + 1) > 0) {
                            data[i][j][k] *= 2;
                        } else {
                            continue;
                        }
                    }
                }
            }


            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data[0].length; j++) {
                    for (int k = 0; k < data[0][0].length; k++) {
                        out.write(data[i][j][k] + "\t");
                    }
                }
                out.newLine(); // Add a newline after each row
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


    // Helper method to calculate average
    private double average(double[] array) {
        double sum = 0;
        for (double value : array) {
            sum += value;
        }

        double avg=sum / array.length;
        return avg;
    }
}
