package com.SundarImages.BhartiImageQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.SundarImages.BhartiImageQuiz.QuizContainer.*;

import java.util.ArrayList;

public class QuizDbHelper  extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "SundarQuiz1.db";
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
        Question q1 = new Question("wi1", "i1","i2","i3", 1);
        addQuestion(q1);
        Question q2 = new Question("wi2", "i1","i2","i3", 2);
        addQuestion(q2);
        Question q3 = new Question("wi3", "i3", "i1", "i2", 1);
        addQuestion(q3);
        Question q4 = new Question("wi4", "i4","i5" ,"i6", 1);
        addQuestion(q4);
        Question q5 = new Question("wi5", "i5", "i6", "i4", 1);
        addQuestion(q5);
        Question q6 = new Question("wi6", "i4","i5", "i6" , 3);
        addQuestion(q6);
        Question q7 = new Question("wi7", "i7","i8", "i9", 1);
        addQuestion(q7);
        Question q8 = new Question("wi8", "i9","i8", "i10", 2);
        addQuestion(q8);
        Question q9 = new Question("wi9",  "i9" ,"i8","i10" ,1);
        addQuestion(q9);
        Question q10 = new Question("wi10", "i11","i9", "i10", 3);
        addQuestion(q10);
        Question q11 = new Question("wi11", "i11", "i12","i13", 1);
        addQuestion(q11);
        Question q12 = new Question("wi12", "i12", "i11","i13", 1);
        addQuestion(q12);
        Question q13 = new Question("wi13", "i11", "i12", "i13", 3);
        addQuestion(q13);
        Question q14 = new Question("wi14", "i12", "i14", "i13", 2);
        addQuestion(q14);
        Question q15 = new Question("wi15", "i14" ,"i16" ,"i15", 3);
        addQuestion(q15);
        Question q16 = new Question("wi16", "i16" ,"i17" ,"i18", 1);
        addQuestion(q16);
        Question q17 = new Question("wi17", "i17", "i18", "i19", 1);
        addQuestion(q17);
        Question q18 = new Question("wi18",  "i19","i17", "i18", 3);
        addQuestion(q18);
        Question q19 = new Question("wi19","i18", "i19" , "i21", 2);
        addQuestion(q19);
        Question q20 = new Question("wi21", "i21", "i23" , "i22", 1);
        addQuestion(q20);
        Question q21 = new Question("wi21", "i22", "i23" , "i21", 3);
        addQuestion(q21);
        Question q22 = new Question("wi22", "i21", "i22", "i23", 2);
        addQuestion(q22);
        Question q23 = new Question("wi23", "i23", "i24", "i25", 1);
        addQuestion(q23);
        Question q24 = new Question("wi24", "i23" ,"i24" ,"i25",  2);
        addQuestion(q24);
        Question q25 = new Question("wi25", "i24" ,"i26" ,"i25", 3);
        addQuestion(q25);
        Question q26 = new Question("wi26", "i26" ,"i27" ,"i25", 1);
        addQuestion(q26);
        Question q27 = new Question("wi27", "i26", "i27" ,"i28", 2);
        addQuestion(q27);
        Question q28 = new Question("wi28", "i27", "i29" ,"i28", 3);
        addQuestion(q28);
        Question q29 = new Question("wi29", "i28", "i30", "i29", 3);
        addQuestion(q29);
        Question q30 = new Question("wi30", "i30" ,"i28" ,"i29", 1);
        addQuestion(q30);
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
