package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.SingleAdServlet", urlPatterns = "/ad")
public class SingleAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
//            Long adId = Long.parseLong(request.getParameter("id"));
//            request.setAttribute("ad", DaoFactory.getAdsDao().findByAdID(adId));
            request.getRequestDispatcher("/ads").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("BAD");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long adId = Long.parseLong(request.getParameter("id"));
            request.setAttribute("ad", DaoFactory.getAdsDao().findByAdID(adId));
            request.getRequestDispatcher("/WEB-INF/ads/single-ad.jsp").forward(request, response);
        } catch (NumberFormatException e) {
            System.out.println("BAD");
        }
    }
}
