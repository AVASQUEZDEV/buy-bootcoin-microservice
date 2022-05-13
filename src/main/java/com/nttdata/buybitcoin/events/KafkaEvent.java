package com.nttdata.buybitcoin.events;

import com.nttdata.buybitcoin.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * This class defines the event model
 *
 * @param <T>
 * @author Alcibar Vasquez
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaEvent<T> {

    private String id;
    private Date date;
    private EventType type;
    private T data;

}
