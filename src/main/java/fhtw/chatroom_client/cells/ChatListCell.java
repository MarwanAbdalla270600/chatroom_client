package fhtw.chatroom_client.cells;


import fhtw.chatroom_client.MainApplication;
import fhtw.chatroom_client.chat.PrivateChat;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.util.Callback;
import lombok.Getter;


@Getter

public class ChatListCell implements Callback<ListView<PrivateChat>, ListCell<PrivateChat>> {

    // ...

    public ListCell<PrivateChat> call(ListView<PrivateChat> param) {
        return new ListCell<>() {
            public void updateItem(PrivateChat privateChat, boolean empty) {
                super.updateItem(privateChat, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if (privateChat != null) {
                    // Create an HBox to hold all the elements
                    HBox hbox = new HBox();
                    hbox.getStyleClass().add("chatcell");

                    Image img;
                    if (privateChat.getFriend().charAt(privateChat.getFriend().length() - 1) == 'f') {
                        img = new Image(String.valueOf(MainApplication.class.getResource("assets/woman.png")));
                    } else {
                        img = new Image(String.valueOf(MainApplication.class.getResource("assets/man.png")));
                    }

                    // Create an ImageView
                    ImageView imageView = new ImageView(img);
                    imageView.setFitHeight(60.0);
                    imageView.setFitWidth(60.0);

                    // Add padding to the ImageView
                    StackPane imagePane = new StackPane(imageView);
                    imagePane.setPadding(new Insets(0, 10, 0, 10)); // Adjust padding as needed

                    // Create a VBox for the labels
                    VBox labelVBox = new VBox();
                    labelVBox.setAlignment(Pos.CENTER_RIGHT);

                    // Create labels
                    String tmp = privateChat.getFriend();
                    tmp = tmp.substring(0, tmp.length() - 1);
                    Label nameLabel = new Label(tmp);
                    nameLabel.setFont(Font.font("Open Sans Bold", 18.0));
                    nameLabel.setTextFill(Color.web("#ffffff"));

                    // Add padding to the VBox
                    labelVBox.setPadding(new Insets(0, 0, 0, 10)); // Adjust padding as needed

                    // Add labels to the VBox
                    labelVBox.getChildren().addAll(nameLabel);

                    // Create a red circle

                    Color color;
                    if (!privateChat.isOnline()) {
                        color = Color.RED;
                    } else {
                        color = Color.GREEN;
                    }
                    Circle redCircle = new Circle(5, color);

                    // Create a Region for horizontal spacing
                    Region spacer = new Region();
                    HBox.setHgrow(spacer, Priority.ALWAYS);

                    // Adjust the vertical alignment of the circle
                    VBox circleContainer = new VBox(redCircle);
                    circleContainer.setAlignment(Pos.CENTER);

                    // Add all elements to the HBox
                    hbox.getChildren().addAll(imagePane, labelVBox, spacer, circleContainer);
                    //hbox.setBackground(new Background(new BackgroundFill(Color.BLUE,null,null)));

                    // Set the HBox as the graphic for the cell
                    setGraphic(hbox);
                } else {
                    setText("null");
                    setGraphic(null);
                }
            }
        };
    }
}