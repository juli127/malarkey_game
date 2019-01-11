package com.gmail.kramarenko104;

import com.gmail.kramarenko104.controller.Game;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;

public class RunApp extends Application {

    final private int WIDTH = 1050;
    final private int HEIGHT = 470;
    final private String descrText = "Add phrase describing";
    final private Font btnFont = new Font("Verdana Bold", 12 );
    final private Font titleFont = new Font("Verdana Bold", 16 );
    final private Font infoFont = new Font("Verdana Bold", 14 );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Game game = new Game();

        primaryStage.setTitle("Malarkey game v1.1      by Kramarenko Iuliia");
        Group group = new Group();
        Scene scene = new Scene(group, WIDTH, HEIGHT);

        VBox strings = new VBox();
        HBox createSentenceBox = new HBox();
        HBox addWhoBox = new HBox();
        HBox addWhatDoesBox = new HBox();
        HBox addWhereBox = new HBox();
        HBox addWhyBox = new HBox();

        group.getChildren().add(strings);
        strings.setPadding(new Insets(10, 10, 10, 10));
        strings.setSpacing(10);

        //--------- CREATE NEW SENTENCE -----------------------
        Text title = new Text("Let's write great masterpiece!");
        title.setFont(titleFont);
        strings.getChildren().add(title);
        strings.getChildren().add(createSentenceBox);
        createSentenceBox.setSpacing(10);

        TextArea sentencesText = new TextArea();
        sentencesText.setMinWidth(900);
        sentencesText.setMinHeight(200);
        Button btnWrite = new Button();
        btnWrite.setFont(btnFont);
        btnWrite.setText("Start writing!");
        btnWrite.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String newSentence = game.createSentence();
                sentencesText.appendText(newSentence + "\n");
                sentencesText.setFocusTraversable(true);
            }
        });
        createSentenceBox.getChildren().add(btnWrite);
        createSentenceBox.getChildren().add(sentencesText);

        Line splitLine = new Line();
        splitLine.setStartX(30);
        splitLine.setEndX(1010);
        strings.getChildren().add(splitLine);

        Text infoText = new Text("You can add some new words to the dictionary:");
        infoText.setFont(infoFont);
        strings.getChildren().add(infoText);

        //--------- WHO --------------------
        strings.getChildren().add(addWhoBox);
        addWhoBox.setSpacing(10);
        addWhoBox.getChildren().add(new Text(descrText));
        addWhoBox.getChildren().add(new Text("           'WHO':"));
        TextField whoText = new TextField();
        whoText.setEditable(true);
        whoText.setMinWidth(500);
        addWhoBox.getChildren().add(whoText);
        whoText.clear();

        Button btnWho = new Button();
        btnWho.setText("Add WHO");
        btnWho.setFont(btnFont);
        btnWho.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.getWho().addWord(whoText.getText());
                whoText.clear();
            }
        });
        addWhoBox.getChildren().add(btnWho);

        //--------- WHAT DOES --------------------
        strings.getChildren().add(addWhatDoesBox);
        addWhatDoesBox.setSpacing(10);
        addWhatDoesBox.getChildren().add(new Text(descrText));
        addWhatDoesBox.getChildren().add(new Text("'WHAT DOES':"));
        TextField whatDoesText = new TextField();
        whatDoesText.setEditable(true);
        whatDoesText.setMinWidth(500);
        addWhatDoesBox.getChildren().add(whatDoesText);
        whatDoesText.clear();

        Button btnWhatDoes = new Button();
        btnWhatDoes.setText("Add WHAT DOES");
        btnWhatDoes.setFont(btnFont);
        btnWhatDoes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.getWhatDoes().addWord(whatDoesText.getText());
                whatDoesText.clear();
            }
        });
        addWhatDoesBox.getChildren().add(btnWhatDoes);

        //--------- WHERE --------------------
        strings.getChildren().add(addWhereBox);
        addWhereBox.setSpacing(10);
        addWhereBox.getChildren().add(new Text(descrText));
        addWhereBox.getChildren().add(new Text("        'WHERE':"));
        TextField whereText = new TextField();
        whereText.setEditable(true);
        whereText.setMinWidth(500);
        addWhereBox.getChildren().add(whereText);
        whereText.clear();

        Button btnWhere = new Button();
        btnWhere.setText("Add WHERE");
        btnWhere.setFont(btnFont);
        btnWhere.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.getWhere().addWord(whereText.getText());
                whereText.clear();
            }
        });
        addWhereBox.getChildren().add(btnWhere);

        //--------- WHY --------------------
        strings.getChildren().add(addWhyBox);
        addWhyBox.setSpacing(10);
        addWhyBox.getChildren().add(new Text(descrText));
        addWhyBox.getChildren().add(new Text("           'WHY':"));
        TextField whyText = new TextField();
        whyText.setEditable(true);
        whyText.setMinWidth(500);
        addWhyBox.getChildren().add(whyText);
        whyText.clear();

        Button btnWhy = new Button();
        btnWhy.setText("Add WHY");
        btnWhy.setFont(btnFont);
        btnWhy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.getWhy().addWord(whyText.getText());
                whyText.clear();
            }
        });
        addWhyBox.getChildren().add(btnWhy);

        //--------- EXIT btn --------------------
        Line splitLine2 = new Line();
        splitLine2.setStartX(30);
        splitLine2.setEndX(1010);
        strings.getChildren().add(splitLine2);

        Button btnExit = new Button();
        btnExit.setText("EXIT");
        btnExit.setFont(btnFont);
        btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game.exit();
            }
        });
        strings.getChildren().add(btnExit);

        /////////////////////////////////////////////
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
