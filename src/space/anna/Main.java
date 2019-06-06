package space.anna;

import unisoft.ws.FNSNDSCAWS2;
import unisoft.ws.FNSNDSCAWS2Port;
import unisoft.ws.fnsndscaws2.request.NdsRequest2;
import unisoft.ws.fnsndscaws2.response.NdsResponse2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("ИНН: ");
        String inn = scanner.nextLine();

        System.out.print("КПП (не обязательно): ");
        String kpp = scanner.nextLine();

        System.out.println("Отправка запроса...");

        NdsResponse2 ndsResponse2 = sendRequest(inn, kpp, LocalDate.now());

        StateResolver stateResolver = new StateResolver();

        for (NdsResponse2.NP np : ndsResponse2.getNP()) {
            System.out.println(stateResolver.resolve(np.getState()));
        }
    }

    private static NdsResponse2 sendRequest(String inn, String kpp, LocalDate currentDate) {
        FNSNDSCAWS2 service = new FNSNDSCAWS2();
        FNSNDSCAWS2Port fnsndscaws2Port = service.getFNSNDSCAWS2Port();

        NdsRequest2 ndsRequest2 = new NdsRequest2();
        NdsRequest2.NP np = new NdsRequest2.NP();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = formatter.format(currentDate);

        np.setDT(date);
        np.setINN(inn);
        np.setKPP(kpp);
        ndsRequest2.getNP().add(np);

        return fnsndscaws2Port.ndsRequest2(ndsRequest2);
    }
}
