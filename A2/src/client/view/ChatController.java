package client.view;

import client.core.ViewHandler;
import client.core.ViewModelFactory;
import client.viewmodel.ChatVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import transferobjects.Message;

import java.io.IOException;

public class ChatController implements ViewController
{
    @FXML
    private Label chatInfoLabel;
    @FXML
    private ListView<String> chatListView;
    @FXML
    private TextField messageField;

    private ChatVM vm;
    private ViewHandler vh;

    @Override
    public void init(ViewHandler vh, ViewModelFactory vmf) throws IOException
    {
        this.vh = vh;
        this.vm = vmf.getChatViewModel();

        chatInfoLabel.textProperty().bind(vm.chatInfoProperty());
        vm.loadMessages();
        chatListView.setItems(vm.messagesObservableList());
        messageField.textProperty().bindBidirectional(vm.newMessageProperty());
    }

    public void onSend(ActionEvent actionEvent)
    {
        if(!messageField.equals(""))
        {
            vm.addNewMessage(new Message(messageField.getText(), vm.getClientUsername()));
            messageField.clear();
        }
    }

    public void onFirst(ActionEvent actionEvent)
    {
        if(!messageField.equals(""))
        {
            vm.addToFirstMessage(new Message(messageField.getText(), vm.getClientUsername()));
            messageField.clear();
        }
    }
}
