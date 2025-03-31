package com.axonivy.connectivity.rest.aynch.chat;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The message being sent over the wire by the {@link ChatService}.
 *
 * @since 7.3.0
 */
public class ChatMessage {
  @JsonProperty
  public String sender;
  @JsonProperty
  public String receiver;
  @JsonProperty
  public Date sentDate;
  @JsonProperty
  public String message;

  @SuppressWarnings("unused")
  private ChatMessage() { // for JACKSON
  }

  public ChatMessage(String sender, String receiver, String message) {
    this.sender = sender;
    this.receiver = receiver;
    this.sentDate = new Date();
    this.message = message;
  }

  @Override
  public String toString() {
    return "@" + sender + ">@" + receiver + ": " + message;
  }
}
