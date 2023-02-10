public class DateTime extends Date {
  private int hour;
  private int minute;
  private int second;

  public DateTime(int year, int month, int day, int hour, int minute, int second) throws DateException {
    super(year, month, day);
    if (!isValidTime(hour, minute, second)) {
      throw new DateException("Illegal time " + String.format("%02d:%02d:%02d", hour, minute, second));
    }
    this.hour = hour;
    this.minute = minute;
    this.second = second;
  }

  public int getHour() {
    return hour;
  }

  public int getMinute() {
    return minute;
  }

  public int getSecond() {
    return second;
  }

  public String toString() {
    return super.toString() + " " + String.format("%02d:%02d:%02d", hour, minute, second);
  }

  private static boolean isValidTime(int hour, int minute, int second) {
    return hour >= 0 && hour <= 23 && minute >= 0 && minute <= 59 && second >= 0 && second <= 59;
  }
}
