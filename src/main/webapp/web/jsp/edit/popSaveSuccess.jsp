<!-- Trigger/Open The Modal -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<button class="btn btn-info" id="btnSave" name="command" value="save" onclick="popDialog('saveModal')">
    <span class="glyphicon glyphicon-floppy-saved"></span>
    Сохранить
</button>
<!-- The Modal -->
<div id="saveModal" class="modal">

    <!-- Modal content -->
    <div class="modal-content">
        <div class="modal-header">
            <h2>Контакт успешно сохранён</h2>
        </div>
        <div class="modal-body">

        </div>
        <div class="modal-footer">

            <!-- write here-->
            <button class="btn btn-info" id="close_saveModal" name="command"
                    value="contact">Закрыть
            </button>


        </div>
    </div>

</div>