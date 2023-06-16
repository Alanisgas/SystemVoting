package ar.edu.unaj.login.service;

import ar.edu.unaj.login.model.Student;
import ar.edu.unaj.login.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Service
public class TelegramBootService extends TelegramLongPollingBot {
    @Autowired
    private StudentRepository studentRepositoryBot;
    @Override
    public void onUpdateReceived(Update update) {
        //se obtiene el mensaje del usuario
        final String menssageboot = update.getMessage().getText();
        System.out.println("Escribieron en el boot" + menssageboot);

        // se obtiene el id de chat del usuario
        final long chatId = update.getMessage().getChatId();

        // se consulta a mongo
      //  Student student = (Student) studentRepositoryBot.findStudentByFile(Integer.parseInt(menssageboot));

        // se crea un objeto mensaje

        String respuesta = obtenerRespuesta(menssageboot);
        SendMessage message = new SendMessage();

        message.setChatId(chatId);
        message.setText(respuesta);
        try {
            // se envia el mensaje
            execute(message);
        }catch (TelegramApiException a){
            a.printStackTrace();
        }
    }

    private String obtenerRespuesta(String menssageTextReceived) {
        String respuesta;
        if (menssageTextReceived.equalsIgnoreCase("Hola")){
            respuesta= "Hola en que puedo ayudarte";
        } else if (menssageTextReceived.equalsIgnoreCase("Adios")) {
            respuesta="Muchas gracias por utilizar este medio";

        }
        else {
            respuesta = "No puedo resolver tu consulta";
        }
        return  respuesta;
    }

    @Override
    public String getBotUsername() {
        return "unajEUbot";
    }
    @Override
    public String getBotToken() {
        return "6252831133:AAERXlIOlrAvt4gyMgeTYxComcUChlQi_e8";
    }



}
