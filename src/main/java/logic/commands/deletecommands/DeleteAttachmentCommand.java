package logic.commands.deletecommands;

import logic.commands.maincommands.UpdateCommand;
import logic.database.AttachmentDAO;
import logic.entity.Attachment;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 12.09.2016.
 */
public class DeleteAttachmentCommand extends UpdateCommand implements ActionCommand{
    public String execute(HttpServletRequest request) {
        String[] selectedFile = request.getParameterValues("check_selected_file");
        for (String aSelectedFile : selectedFile) {
            this.deleteAttachment(request, Integer.parseInt(aSelectedFile));
        }
        super.fillAllParameters(request);

        return getProperty("path.page.edit");
    }


    public boolean deleteAttachment(HttpServletRequest request, final int ATTACHMENTID) {
        Employee employee = getEmployeeFromSession(request);
        List<Attachment> attachmentList = employee.getAttachmentList();
        Attachment removeFile = null;
        for(Attachment attachment : attachmentList){
            if(attachment.getId()==ATTACHMENTID){
                removeFile = attachment;
            }
        }
        removeFile.setDeleted(true);
        removeFile.setSaved(true);
        deleteAttachmentFromDB(ATTACHMENTID);
        return true;
    }


    public boolean deleteAttachmentFromDB(final int ATTACHMENTID){
        AttachmentDAO attachmentDAO = new AttachmentDAO();
        attachmentDAO.deleteAttachment(ATTACHMENTID);
        return true;
    }
    public Employee getEmployeeFromSession(HttpServletRequest request){
        Employee employee = (Employee)request.getSession().getAttribute("employee");
        return employee;
    }


}
