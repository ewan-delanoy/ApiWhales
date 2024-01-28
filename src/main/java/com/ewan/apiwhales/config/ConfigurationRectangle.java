package com.ewan.apiwhales.config;

public class ConfigurationRectangle {

    private static final int NOMBRE_DEMPLACEMENTS = 16;

    public static int indexUnique(int numeroFile,int numEmplacement) {
        return (numeroFile-1)*NOMBRE_DEMPLACEMENTS + numEmplacement;
    }

    public static int numEmplacement(int indexUnique) {
        int r = (indexUnique % NOMBRE_DEMPLACEMENTS);
        return r == 0 ?  NOMBRE_DEMPLACEMENTS : r ;
    }

    public static int numeroFile(int indexUnique) {
        int n = numEmplacement(indexUnique);
        return ((indexUnique - n) / NOMBRE_DEMPLACEMENTS ) + 1 ;
    }


}
