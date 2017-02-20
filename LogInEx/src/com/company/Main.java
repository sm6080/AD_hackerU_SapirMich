package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
/* מדפיסה למשתמש תפריט של אפשרויות ,יש גם אפשרות לצאת מהאפליקציה. האפשרויות הבאות: אפשר לעשות SIGNUP - הרשמה ואז המחשב מבקש סיסמא.
    משתמש יכול להיות אך ורק תווים שהם אותיות קטנות אבל יכול להיגמר במספר ובוודאי שלם. המחשב אומר לו האם השם תפוס או  פנוי .
    אם תפוס אז יש לו אפשרות להקליד שם אחר או להקיש 0 וENTER ואז חוזר למסך הראשי ושם יש לו אופציה לצאת מהאפליקציה. אם הקליד שם לא נכון המערכת תודיע לו על כך.
   אחרי שהשם תקין המערכת תבקש לבחור סיסמא, היא יכולה להיות כל תו מ 48 עד 126 (באסקי) והיא חייבת להיות 4-12 תווים. אחרי שהכניס סיסמא נדרש להכניס שוב לאימות,
    אם בפעם ה 2 הסיסמא לא תואמת נבקש מחדש את הפעם הראשונה.אם הצליח בפעם ה 2 זה יומר לו מזל טוב וייכנס לתפריט הראשי. מבחינת UX כאשר השם לא טוב לא להמשיך איתו
     אפשרויות נוספות :
     LOG IN - למי שכבר יש את שניהם, הוא מכניס לוחץ אנטר ואז סיסמא ולוחץ אנטר
     אפציה שלישית :
     LOG-OUT - מנתק אותך אם אתה מחובר , אם עשה זאת לפני ההרשמה או הכניסה,
     נאמר לו שא"א לפני ההרשמה או הכניסה
     אופציה נוספת:
     אפשר למשתמש :יכניס STRING ויקבל למסך את אותה מחרוזת בהיפוך סדר התווים , השירות הזה ניתן רק למשתמשים מחוברים. אם לא מחובר, יודיע שא"א לאלו שלא מחוברים
     וכמובן אופציה של יציאה. כמובן שאחרי פעולה חוזרים לתפריט הראשי
     */

    public static void main(String[] args) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        LinkedList<User> users = new LinkedList<User>();
        home(bufferedReader, users);


    }

    private static String input(BufferedReader bufferedReader) {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User enterUserName(BufferedReader bufferedReader, LinkedList<User> users) {
        System.out.println("Enter user name");
        String userName = input(bufferedReader);
        User u = new User(userName);
        if (User.correctUser(userName)) {
            if (!u.isUserAvailable(users, userName)) {
                u.setPassword(enterPassword(bufferedReader));
                return u;
            } else {
                System.out.println("The user already exist ");
            }
        } else {
            System.out.println("The user name is not correct ");

        }
        return enterUserName(bufferedReader, users);
    }

    private static String enterPassword(BufferedReader bufferedReader) {
        System.out.println("Enter Password");
        String password = input(bufferedReader);
        System.out.println("Enter validation password");
        String passwordValid = input(bufferedReader);
        if (User.correctPassword(password)) {
            if (User.validationPassword(password, passwordValid))
                return password;
            else
                System.out.println("The validation is not match, Please enter password again");

        } else {
            System.out.println("The password is not correct");
        }
        return enterPassword(bufferedReader);
    }


    public static void home(BufferedReader bufferedReader, LinkedList<User> users) {

        System.out.println("enter choice : 1- SIGN-UP  2- LOG-IN   3-LOG-OUT   4-EXIT");
        try {
            String choice = bufferedReader.readLine();
            switch (choice) {
                case "1":
                    users.add(enterUserName(bufferedReader, users));
                    System.out.println("Welcome");
                    break;
                case "2":
                    if (logIn(bufferedReader,users))
                        System.out.println("Congratulations!");
                    break;
                case "3":
                    home(bufferedReader, users);
                    break;
                case "4":
                    break;
                default:
                    System.out.println("It is not a correct choice");

            }
        } catch (IOException ex) {
            System.out.println("error reading");
        } catch (NumberFormatException ex) {
            System.out.println("Must enter int");
        }
    }


    private static boolean login(BufferedReader bufferedReader,LinkedList<User> users) {
        System.out.println("enter your user name");
        String userName=input(bufferedReader);
        System.out.println("enter password");
        String password=input(bufferedReader);
        User u=new User(userName,password);
        if(u.isLogOned(users,u))
            return true;
        return false;
    }


}
