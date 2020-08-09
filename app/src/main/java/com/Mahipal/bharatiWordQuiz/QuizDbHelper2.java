package com.Mahipal.bharatiWordQuiz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.Mahipal.bharatiWordQuiz.QuizContainer.QuestionsTable;

import java.util.ArrayList;

public class QuizDbHelper2 extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Quiz2.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    public QuizDbHelper2(Context context) {
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
        Question p1 = new Question("p1", "मटर","मगर","मटन", 1);
        addQuestion(p1);
        Question p2 = new Question("p2", "मदत","मद्त","मदद", 3);
        addQuestion(p2);
        Question p3 = new Question("p3", "शहर", "सहर", "साारा", 1);
        addQuestion(p3);
        Question p4 = new Question("p4", "जीवक","जीवन" ,"चमक", 2);
        addQuestion(p4);
        Question p5 = new Question("p5", "किताब", "कीताब", "किनारा", 1);
        addQuestion(p5);
        Question p6 = new Question("p6", "मटर","किनारा", "विचार" , 3);
        addQuestion(p6);
        Question p7 = new Question("p7", "आधार","अधार", "आदर", 1);
        addQuestion(p7);
        Question p8 = new Question("p8", "यकिन","यकीन", "यखीन", 2);
        addQuestion(p8);
        Question p9 = new Question("p9",  "अमन" ,"अमर","आकार" ,1);
        addQuestion(p9);
        Question p10 = new Question("p10", "लडकि","लडकी", "लकडी", 3);
        addQuestion(p10);
        Question p11 = new Question("p11", "अगला", "अकल","पगला", 1);
        addQuestion(p11);
        Question p12 = new Question("p12", "अमन" ,"अमर","आकार" , 2);
        addQuestion(p12);
        Question p13 = new Question("p13", "चलाना", "चलान", "चलना", 3);
        addQuestion(p13);
        Question p14 = new Question("p14", "कमर", "कागज", "काघज", 2);
        addQuestion(p14);
        Question p15 = new Question("p15", "समहू" ,"समुह" ,"समूह", 3);
        addQuestion(p15);
        Question p16 = new Question("p16", "सदैव" ,"सॅदव" ,"सदेव", 1);
        addQuestion(p16);
        Question p17 = new Question("p17", "दूसरा", "दुसरा", "दसरा", 1);
        addQuestion(p17);
        Question p18 = new Question("p18",  "अपर","उपर", "ऊपर", 3);
        addQuestion(p18);
        Question p19 = new Question("p19","शरिर", "शरीर" , "शारीर", 2);
        addQuestion(p19);
        Question p20 = new Question("p20", "सवाल", "चावल" , "बवाल", 1);
        addQuestion(p20);
        Question p21 = new Question("p21", "जाहज", "जाहाज" , "जहाज", 3);
        addQuestion(p21);
        Question p22 = new Question("p22", "अमन" ,"अमर","अमीर" , 3);
        addQuestion(p22);
        Question p23 = new Question("p23", "सरल", "सडक", "सदका", 2);
        addQuestion(p23);
        Question p24 = new Question("p24", "सडक" ,"सतह" ,"सरल",  2);
        addQuestion(p24);
        Question p25 = new Question("p25", "रखना" ,"रखना" ,"रहना", 3);
        addQuestion(p25);
        Question p26 = new Question("p26", "गहरा" ,"पहरा" ,"कहर", 1);
        addQuestion(p26);
        Question p27 = new Question("p27", "वीमान", "विमान" ,"वामन", 2);
        addQuestion(p27);
        Question p28 = new Question("p28", "जहाज", "जगाह" ,"जगह", 3);
        addQuestion(p28);
        Question p29 = new Question("p29", "हजिर", "हजारो", "हजार", 3);
        addQuestion(p29);
        Question p30 = new Question("p30", "आकार" ,"अकार" ,"आकर", 1);
        addQuestion(p30);
        Question p31 = new Question("p31", "भ्रमन" ,"गरम" ,"ग्रहन",2);
        addQuestion(p31);
        Question p32 = new Question("p32", "ईकाइ" ,"इकाइ" ,"इकाई", 3);
        addQuestion(p32);
        Question p33 = new Question("p33", "सतह" ,"सफर" ,"सरल" ,2);
        addQuestion(p33);
        Question p34 = new Question("p34", "शिकार" ,"सीतारा" ,"सितारा" ,3);
        addQuestion(p34);
        Question p35 = new Question("p35", "सागर", "शहर" ,"सेहत", 1);
        addQuestion(p35);
        Question p36 = new Question("p36", "मिनट", "मिणट", "मिनिट",1);
        addQuestion(p36);
        Question p37 = new Question("p37", "लहर", "सहर", "पहर", 1);
        addQuestion(p37);
        Question p38 = new Question("p38", "सदन", "कदम", "मदन",2);
        addQuestion(p38);
        Question p39 = new Question("p39", "सुबाह", "सूबह", "सुबह", 3);
        addQuestion(p39);
        Question p40 = new Question("p40", "सरल" ,"सराफ" ,"सडक", 1);
        addQuestion(p40);

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
