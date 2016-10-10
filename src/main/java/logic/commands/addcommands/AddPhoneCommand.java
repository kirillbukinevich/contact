package logic.commands.addcommands;

import logic.commands.maincommands.UpdateCommand;
import logic.entity.ContactPhone;
import logic.entity.Employee;
import logic.processcommand.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static logic.configuration.ConfigurationManager.getProperty;

/**
 * Created by aefrd on 10.09.2016.
 */
public class AddPhoneCommand extends UpdateCommand implements ActionCommand {
    public String execute(HttpServletRequest request) {
        Employee employee = getEmployeeFromSession(request);
        addPhone(request, employee);
        super.fillAllParameters(request);
        return getProperty("path.page.edit");
    }


    public void addPhone(HttpServletRequest request, Employee employee) {
        final int EMPLOYEEID = employee.getId();
        ContactPhone phone = getPhone(request);
        phone.setEmployeeID(EMPLOYEEID);
        employee.getPhoneList().add(phone);

    }

    public ContactPhone getPhone(HttpServletRequest request) {
        ContactPhone phone = new ContactPhone();
        phone.setCodeCountry(Integer.valueOf(request.getParameter("code_country")));
        phone.setCodeOperator(Integer.valueOf(request.getParameter("code_operator")));
        phone.setNumber(Integer.parseInt(request.getParameter("phone_number")));
        phone.setType(request.getParameter("phone_type"));
        phone.setComment(request.getParameter("comment"));
        phone.setId(phone.hashCode());

        return phone;
    }
}
