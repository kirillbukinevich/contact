
package logic.commands.deletecommands;

import logic.commands.maincommands.ContactCommand;
import logic.configuration.ConfigurationManager;
import logic.database.EmployeeDAO;
import logic.processcommand.ActionCommand;
import org.apache.commons.io.FileUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import static logic.configuration.LogConfiguration.LOGGER;

public class DeleteCommand implements ActionCommand {

    public String execute(HttpServletRequest request) {
        String[] selectedEmpl = request.getParameterValues("check_selected");

        for (String aSelectedEmpl : selectedEmpl) {
            this.deleteEmployee(Integer.parseInt(aSelectedEmpl));
        }
        ContactCommand contactCommand = new ContactCommand();
        return contactCommand.execute(request);
    }

    public boolean deleteEmployee(final int ID) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.deleteEmployee(ID);
        deleteAttachmentDirectory(ID);
        return true;
    }

    public boolean deleteAttachmentDirectory(final int ID) {
        String path = ConfigurationManager.getProperty("path.saveFile") + ID;
        try {
            FileUtils.deleteDirectory(new File(path));
            LOGGER.info("deleted directory from server");
        } catch (IOException e) {
            LOGGER.error("can't delete directory from server " + e);
        }
        return true;
    }
}
