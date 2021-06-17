import dao.AccountsDAOImpl;
import dao.CardsDAOImpl;
import services.*;
import controller.Controller;
import utils.ReadContentFile;

import static utils.DatabaseConnection.*;

public class Main {
    public static void main(String[] args) {
        String schema = "src/main/resources/schema.sql";
        String data = "src/main/resources/data.sql";
        createOrFillDatabase(ReadContentFile.readUsingFiles(schema));
        createOrFillDatabase(ReadContentFile.readUsingFiles(data));
        Controller controller = new Controller();
        AccountService.getInstance().setAccountsDAOImpl(AccountsDAOImpl.getInstance());
        CardService.getInstance().setCardsDAOImpl(CardsDAOImpl.getInstance());
        controller.setAccountsService(AccountService.getInstance());
        controller.setCardsService(CardService.getInstance());
        controller.runServer();
    }
}
