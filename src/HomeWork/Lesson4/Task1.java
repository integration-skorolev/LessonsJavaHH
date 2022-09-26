package HomeWork.Lesson4;

import java.util.regex.Pattern;

/**
 * 1. Создать статический метод который принимает на вход три параметра: login, password и confirmPassword.
 * 2. Login должен содержать только латинские буквы, цифры и знак подчеркивания.
 * 3. Длина login должна быть меньше 20 символов. Если login не соответствует этим требованиям, необходимо выбросить WrongLoginException.
 * 4. Password должен содержать только латинские буквы, цифры и знак подчеркивания. Длина password должна быть меньше 20 символов. Также password и confirmPassword должны быть равны. Если password не соответствует этим требованиям, необходимо выбросить WrongPasswordException.
 * 5. WrongPasswordException и WrongLoginException - пользовательские классы исключения с двумя конструкторами – один по умолчанию, второй принимает сообщение исключения и передает его в конструктор класса Exception.
 * 6. Обработка исключений проводится внутри метода.
 * 7. Используем multi-catch block.
 * 8 .Метод возвращает true, если значения верны или false в другом случае.
 */

public class Task1 {

 private static final String REGEX = "^.*(?=.*[a-zA-Z])(?=.*\\d)(?=.*_)^.{3,19}$.*$";

  public static boolean checkUser(String login, String password, String confirmPassword) {
    try {
      Pattern pattern = Pattern.compile(REGEX);
      if (!pattern.matcher(login).matches()) {
        throw new WrongLoginException(String.format("Логин - %s - должен быть короче 20 символов и содержать только " +
            "латинские буквы, цифры и знак подчеркивания", login));
      } else if (!pattern.matcher(password).matches() || !password.equals(confirmPassword)) {
        throw new WrongPasswordException(String.format("Пароль - %s - должен быть короче 20 символов и содержать только " +
            "латинские буквы, цифры и знак подчеркивания. Пароли должны совпадать.", password));
      }
    } catch (WrongLoginException | WrongPasswordException e) {
      e.printStackTrace();
      return false;
    }
    return true;
  }
}
