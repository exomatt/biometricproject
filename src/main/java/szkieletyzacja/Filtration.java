package szkieletyzacja;

public class Filtration {

    public static short[][] minutiaeFiltration(short tabMinucje[][]) {
        for (int i = 8; i < tabMinucje.length - 7; i = i + 2) {
            for (int j = 8; j < tabMinucje[0].length - 7; j = j + 2) {
                if (tabMinucje[i][j] != 0) {
                    int minucje = 0;
                    //zliczam ile jest minucji wokół przy masce 15x15
                    for (int k = i - 7; k < i + 7; k++)
                        for (int l = j - 7; l < j + 7; l++)
                            if (tabMinucje[k][l] != 0)
                                minucje++;

                    int minucji_do_usuniecia = (int) (minucje * 0.8);

                    for (int m = i - 7; m < i + 7; m++) {
                        for (int l = j - 7; l < j + 7; l++) {
                            if (minucji_do_usuniecia == 0) break;
                            if (tabMinucje[m][l] != 0) {
                                tabMinucje[m][l] = 0;
                                minucji_do_usuniecia--;
                            }
                        }
                        if (minucji_do_usuniecia == 0) break;
                    }
                }
            }
        }
        return tabMinucje;
    }
}
