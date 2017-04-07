package projekt.model;

import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Created by miQ333 on 29.12.2016.
 */
public class ProjektCheckRecord {
    public static boolean checkImie(String imie){
        if(imie.length() < 21){
            return imie.matches("\\p{IsAlphabetic}{2,}");
        }

        return false;
    }

    public static boolean checkNazwisko(String nazwisko){
        if(nazwisko.length() < 31){
            return nazwisko.matches("\\p{IsAlphabetic}{2,}");
        }
        return false;
    }

    public static boolean checkEmail(String email){
        if(email.length()< 31){
            return email.matches("[\\p{IsAlphabetic}\\p{IsDigit}\\.-]+@\\p{IsAlphabetic}+\\p{IsDigit}*\\p{IsAlphabetic}*\\.\\p{IsAlphabetic}+");
        }
        return false;
    }

    public static boolean checkPesel(String pesel){
        return pesel.matches("\\p{Digit}{11}");

    }

    public static boolean checkUlica(String ulica){
        if(ulica.length() < 41){
            return ulica.matches("[\\p{IsAlphabetic}\\p{IsDigit}\\. -]{2,}");
        }
        return false;
    }

    public static boolean checkNumer(String numer){
        if(numer.length() < 11){
            return numer.matches("[\\p{IsAlphabetic}\\p{IsDigit}\\./]{1,}");
        }
        return false;
    }

    public static boolean checkMiejscowosc(String miejscowosc){
        if(miejscowosc.length() < 41){
            return miejscowosc.matches(("\\p{IsAlphabetic}{2,}"));
        }
        return false;
    }

    public static boolean checkKod(String kod){
        return kod.matches("\\p{IsDigit}{2}-\\p{IsDigit}{3}");
    }

    public static boolean checkPensja(String pensja){
        return pensja.matches("\\p{IsDigit}{3,8}[,\\.]{0,1}\\p{IsDigit}{1,2}");
    }

    public static boolean checkNazwa(String nazwa){
        return nazwa.matches("\\p{IsAlphabetic}{0,20}");
    }

    public static boolean checkProcentAlkoholu(String procentAlkoholu){
        if(procentAlkoholu.matches("\\p{IsDigit}{0,2}[,\\.]{0,1}\\p{IsDigit}{1,2}")){
            if(Double.parseDouble(procentAlkoholu) > 40 || Double.parseDouble(procentAlkoholu) < 0){
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean checkTypPiwa(String typPiwa){
        return typPiwa.matches("\\p{IsAlphabetic}{0,20}");
    }

    public static boolean checkCena(String cena){
        return cena.matches("\\p{IsDigit}{1,4}[,\\.]{0,1}\\p{IsDigit}{0,2}");
    }

    public static boolean checkData(String data){
        LocalDate currentDate = LocalDate.now(ZoneId.of("Europe/Paris"));

        String[] currentDateTable = (currentDate.toString()).split("-");
        if(data.matches("\\p{IsDigit}{4}-\\p{IsDigit}{2}-\\p{IsDigit}{2}")){
            String[] dataTable =data.split("-");
            int year = Integer.parseInt(dataTable[0]);
            int month = Integer.parseInt(dataTable[1]);
            int day = Integer.parseInt(dataTable[2]);
            int currentYear = Integer.parseInt(currentDateTable[0]);
            int currentMonth = Integer.parseInt(currentDateTable[1]);
            int currentDay = Integer.parseInt(currentDateTable[2]);
            boolean correctDate;

            if(year <= currentYear){
                if(year < currentYear){
                    correctDate = true;
                } else {
                    if(month <= currentMonth){
                        if(month < currentMonth){
                            correctDate = true;
                        } else {
                            if(day <= currentDay){
                                correctDate = true;
                            }else{
                                correctDate = false;
                            }
                        }
                    } else {
                        correctDate = false;
                    }
                }
            } else{
                correctDate = false;
            }
            double leapYear;

            if(correctDate){
                if(year > 2000){
                    if(month > 0 && month < 13){
                        switch(month) {
                            case 1:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;

                            case 2:
                                leapYear = (double)year / (double)4;
                                boolean isLeapYear;
                                System.out.println("ROK PRZESTEPNY" +leapYear);
                                if (leapYear == (year / 4)) {
                                    if (year % 100 == 0) {
                                        leapYear = year / 400;
                                        if (leapYear == (year / 400)) {
                                            isLeapYear = true;
                                        } else {
                                            isLeapYear =false;
                                        }

                                    } else {
                                        isLeapYear= true;
                                    }
                                } else {
                                    isLeapYear=false;
                                }
                                System.out.println("JEST PRZESTEPNY " + isLeapYear);
                                if(isLeapYear){
                                    if(day > 29 || day < 1){
                                        return false;
                                    } else {
                                        return  true;
                                    }
                                } else {
                                    if (day > 28 || day < 1) {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }

                            case 3:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;

                            case 4:
                                if (day > 30 || day < 1) {
                                    return false;
                                }
                                return true;

                            case 5:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;


                            case 6:
                                if (day > 30 || day < 1) {
                                    return false;
                                }
                                return true;

                            case 7:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;


                            case 8:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;


                            case 9:
                                if (day > 30 || day < 1) {
                                    return false;
                                }
                                return true;

                            case 10:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;


                            case 11:
                                if (day > 30 || day < 1) {
                                    return false;
                                }
                                return true;


                            case 12:
                                if (day > 31 || day < 1) {
                                    return false;
                                }
                                return true;



                            default: return false;
                        }
                    }else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkCyfra(String cyfra){
        System.out.println(cyfra);
        if(Integer.parseInt(cyfra) > 0 && Integer.parseInt(cyfra) < 5 ){
            return false;
        }
        System.out.println(cyfra.matches("\\p{IsDigit}{1}"));
        return cyfra.matches("\\p{IsDigit}{1}");
    }
}
