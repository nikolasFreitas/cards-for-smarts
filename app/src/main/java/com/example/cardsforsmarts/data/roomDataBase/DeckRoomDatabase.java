package com.example.cardsforsmarts.data.roomDataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cardsforsmarts.data.dao.CardDAO;
import com.example.cardsforsmarts.data.dao.DeckDAO;
import com.example.cardsforsmarts.data.entity.Card;
import com.example.cardsforsmarts.data.entity.Deck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Deck.class, Card.class}, version = 2, exportSchema = false)
abstract public class DeckRoomDatabase extends RoomDatabase {
    public abstract DeckDAO deckDAO();
    public abstract CardDAO cardDAO();

    private static volatile DeckRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseDeckExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static DeckRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DeckRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DeckRoomDatabase.class, "deck_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


}
