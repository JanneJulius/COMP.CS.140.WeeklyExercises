public class Date {

  private int year;
  private int month;
  private int day;

  public Date(int year, int month, int day) throws DateException {
    if (!isValidDate(year, month, day)) {
      throw new DateException("Illegal date " + day + "." + month + "." + year);
    }
    this.year = year;
    this.month = month;
    this.day = day;
  }

  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public String toString() {
    return String.format("%02d.%02d.%04d", day, month, year);
  }

  private static boolean isLeapYear(int year) {
    return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
  }

  private static boolean isValidDate(int year, int month, int day) {
    if (month < 1 || month > 12) {
      return false;
    }

    int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    if (isLeapYear(year)) {
      daysInMonth[1] = 29;
    }

    return day > 0 && day <= daysInMonth[month - 1];
  }

}