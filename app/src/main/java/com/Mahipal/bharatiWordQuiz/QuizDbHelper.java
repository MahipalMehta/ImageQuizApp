package com.Mahipal.bharatiWordQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.Mahipal.bharatiWordQuiz.QuizContainer.*;

import java.util.ArrayList;

public class QuizDbHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz1.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public QuizDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }
    private void fillQuestionsTable() {
        Question q1 = new Question("q1", "गाय","गया","गाया", 1);
        addQuestion(q1);
        Question q2 = new Question("q2", "काला","कला","कल", 3);
        addQuestion(q2);
        Question q3 = new Question("q3", "खत", "खेत", "खाता", 1);
        addQuestion(q3);
        Question q4 = new Question("q4", "ग्रह","घर" ,"गर", 2);
        addQuestion(q4);
        Question q5 = new Question("q5", "चल", "चला", "चाल", 1);
        addQuestion(q5);
        Question q6 = new Question("q6", "छल","छाता", "छत" , 3);
        addQuestion(q6);
        Question q7 = new Question("q7", "छल","छत", "छाता", 1);
        addQuestion(q7);
        Question q8 = new Question("q8", "तप","ठग", "ठप", 2);
        addQuestion(q8);
        Question q9 = new Question("q9",  "डर" ,"डाक","डाला" ,1);
        addQuestion(q9);
        Question q10 = new Question("q10", "ताना","तप", "तन", 3);
        addQuestion(q10);
        Question q11 = new Question("q11", "दम", "दाम","धन", 1);
        addQuestion(q11);
        Question q12 = new Question("q12", "दल", "दाल","दान", 1);
        addQuestion(q12);
        Question q13 = new Question("q13", "नारी", "नारा", "नर", 3);
        addQuestion(q13);
        Question q14 = new Question("q14", "नाली", "नल", "नाला", 2);
        addQuestion(q14);
        Question q15 = new Question("q15", "पार" ,"पारा" ,"पर", 3);
        addQuestion(q15);
        Question q16 = new Question("q16", "पल" ,"पाल" ,"फल", 1);
        addQuestion(q16);
        Question q17 = new Question("q17", "फल", "पल", "पाल", 1);
        addQuestion(q17);
        Question q18 = new Question("q18",  "बन","बाम", "बम", 3);
        addQuestion(q18);
        Question q19 = new Question("q19","बला", "बल" , "बाल", 2);
        addQuestion(q19);
        Question q20 = new Question("q20", "भर", "भ्रम" , "भाग", 1);
        addQuestion(q20);
        Question q21 = new Question("q21", "माना", "मान" , "मन", 3);
        addQuestion(q21);
        Question q22 = new Question("q22", "मारा", "मर", "मरा", 2);
        addQuestion(q22);
        Question q23 = new Question("q23", "रथ", "रात", "राग", 1);
        addQuestion(q23);
        Question q24 = new Question("q24", "लाढ" ,"लड" ,"लाद",  2);
        addQuestion(q24);
        Question q25 = new Question("q25", "सखा" ,"साचा" ,"सच", 3);
        addQuestion(q25);
        Question q26 = new Question("q26", "साफ" ,"सखा" ,"सच", 1);
        addQuestion(q26);
        Question q27 = new Question("q27", "हम", "हल" ,"हाल", 2);
        addQuestion(q27);
        Question q28 = new Question("q28", "हक", "हल" ,"हम", 3);
        addQuestion(q28);
        Question q29 = new Question("q29", "सात", "सभी", "सही", 3);
        addQuestion(q29);
        Question q30 = new Question("q30", "गली" ,"गाली" ,"घडी", 1);
        addQuestion(q30);
        Question q31 = new Question("q31", "नारी" ,"नदी" ,"नमी",2);
        addQuestion(q31);
        Question q32 = new Question("q32", "गाना" ,"माना" ,"गान",1 );
        addQuestion(q32);
        Question q33 = new Question("q33", "कान" ,"पान" ,"फन" ,2);
        addQuestion(q33);
        Question q34 = new Question("q34", "पान" ,"फन" ,"कान" ,3);
        addQuestion(q34);
        Question q35 = new Question("q35", "हाथ", "हाथी" ,"साथ", 1);
        addQuestion(q35);
        Question q36 = new Question("q36", "बला", "बल" , "बाल",3);
        addQuestion(q36);
        Question q37 = new Question("q37", "पाठ", "पात", "पता", 1);
        addQuestion(q37);
        Question q38 = new Question("q38", "ताक", "नाक", "चाक",2);
        addQuestion(q38);
        Question q39 = new Question("q39", "चारु", "चारा", "चार", 3);
        addQuestion(q39);
        Question q40 = new Question("q40", "जान" ,"जाना" ,"जल", 1);
        addQuestion(q40);

    }
    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);
        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
