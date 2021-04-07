package PDFreader.view;

import PDFreader.controller.generator.BelegdatenGenerator;

public class testReader {

    public static void main(String[] args) throws Exception {

        BelegdatenGenerator beleg = new BelegdatenGenerator();
        beleg.belegGenerieren();

    }
}
