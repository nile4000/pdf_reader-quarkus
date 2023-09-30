package dev.lueem.ai;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionRequest.ChatCompletionRequestFunctionCall;
import com.theokanning.openai.completion.chat.ChatFunction;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.FunctionExecutor;
import com.theokanning.openai.service.OpenAiService;

import dev.lueem.ai.OpenAiFunctions.Articles;
import dev.lueem.ai.OpenAiFunctions.ArticlesResponse;

public class OpenAiClient {

    private OpenAiService service;
    private FunctionExecutor functionExecutor;
    private String documentContent;

    public OpenAiClient() {


    }

    public String askQuestion(String question) {

        // Verwenden Sie ein CompletionRequest-Objekt statt ChatCompletionRequest
        // (sofern Ihre Bibliothek dies unterstützt)
        // CompletionRequest request = CompletionRequest.builder()
        // .model("davinci-002")
        // .prompt(question) // Verwenden Sie `prompt` anstelle von `messages`
        // .n(1)
        // .maxTokens(1000)
        // .build();

        // // Die Antwort-Methode kann sich je nach Ihrer Client-Bibliothek ändern
        // String response =
        // service.createCompletion(request).getChoices().get(0).getText(); // Annahme,
        // dass es eine
        // // `getText` Methode gibt

        // return response;
        // Erstellen Sie eine einfache Nachrichtenliste, die nur Ihre Frage enthält
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatMessageRole.USER.value(), question));

        ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo-16k-0613")
                .messages(messages)
                .n(1)
                .maxTokens(10000)
                .build();

        ChatMessage responseMessage = service.createChatCompletion(request).getChoices().get(0).getMessage();
        return responseMessage.getContent();
    }

}
