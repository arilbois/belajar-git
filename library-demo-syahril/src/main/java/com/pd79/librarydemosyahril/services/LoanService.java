package com.pd79.librarydemosyahril.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.pd79.librarydemosyahril.models.LoanDetail;

public class LoanService {

  public static BigDecimal calculateLoanPricePerDay(BigDecimal loanPriceBook, Integer qty, Integer diffrentDay) {
    BigDecimal loanPricePerDay = loanPriceBook.multiply(new BigDecimal(qty)).multiply(new BigDecimal(diffrentDay));
    return loanPricePerDay;
  }

  public static Integer calculateStockBookLoan(Integer stock, Integer qty) {
    Integer stockBook = stock - qty;
    return stockBook;
  }

  public static Integer calculateStockBookReturn(Integer stock, Integer qty) {
    Integer stockBook = stock + qty;
    return stockBook;
  }

  public static Integer getTotalBook(List<LoanDetail> listLoanDetail) {
    Integer result = listLoanDetail.size();
    return result;
  }

  public static Integer getDiffrentDay(Date actualReturnDate, Date returnDate) {
    int daysdiff = 0;
    long diff = actualReturnDate.getTime() - returnDate.getTime();
    long diffDays = diff / (24 * 60 * 60 * 1000);
    daysdiff = (int) diffDays;

    return daysdiff;
  }

  public static BigDecimal calculateLateChargerPrice(List<LoanDetail> listDetail, Integer differentday) {

    BigDecimal lateChargerPrice = new BigDecimal("0");
    BigDecimal percent = new BigDecimal(0);
    System.out.println("tambah git.......");

    for (LoanDetail loanDetails : listDetail) {
      lateChargerPrice = lateChargerPrice.add(loanDetails.getLoanPricePerDay());
      System.out.println(lateChargerPrice + "+++++++++++=++++++++++++++++++++++++++++++++++");
    }

    if (differentday == 1) {
      percent = percent.add(new BigDecimal(0.3));

    } else if (differentday <= 3) {
      percent = percent.add(new BigDecimal(0.5));

    } else {
      percent = percent.add(new BigDecimal(0.75));
    }
    System.out.println(differentday + "+++++++++++=++++++++++++++++++++++++++++++++++");
    System.out.println(percent + "+++++++++++=++++++++++++++++++++++++++++++++++");

    lateChargerPrice = (lateChargerPrice.multiply(new BigDecimal(differentday)))
        .multiply(percent);
    return lateChargerPrice;

  }

  public static Boolean isLateChargerDay(Date returnDate, Date actualReturnDate) {
    Boolean result = false;
    if (returnDate.before(actualReturnDate) || returnDate.equals(actualReturnDate)) {
      result = true;
    } else {
      result = false;
    }
    return result;
  }

}
