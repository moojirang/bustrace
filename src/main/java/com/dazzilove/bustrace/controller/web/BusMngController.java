package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.DataGatherScheduler;
import com.dazzilove.bustrace.domain.Route;
import com.dazzilove.bustrace.domain.TripPlan;
import com.dazzilove.bustrace.service.BusRouteService;
import com.dazzilove.bustrace.service.web.RouteService;
import com.dazzilove.bustrace.service.web.TripPlanService;
import com.dazzilove.bustrace.service.wsdl.BusRouteInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BusMngController {

    @Autowired
    private BusRouteService busRouteService;

    @Autowired
    private RouteService routeService;

    @Autowired
	private TripPlanService tripPlanService;

    @RequestMapping("/busMng/busMngList")
    public ModelAndView viewBusMng() throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/busMngList");

        List<Route> routeList = routeService.getRoutes();
        mav.addObject("routeList", routeList);

        return mav;
    }

    @RequestMapping("/busMng/viewAddRoute")
    public ModelAndView viewAddRoute(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/routeForm");
        mav.addObject("pageMode", "ADD");
        return mav;
    }

    @RequestMapping("/busMng/addRoute")
    @ResponseBody
    public String addRoute(ServletRequest request) throws Exception {

        Route route = convertRouteByRequest(request);

        if(!route.isAddValidate()) {
            return "값이 올바르지 않습니다. 필요한 값을 모두 입력했는지 확인하세요.";
        }

        try {
            routeService.addRoute(route);
        } catch(Exception e) {
            e.printStackTrace();
            return "등록 중 에러가 발생 했습니다.";
        }

        return "등록 완료 되었습니다.";
    }

    @RequestMapping("/busMng/viewEditRoute")
    public ModelAndView viewEditRoute(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/routeForm");
        mav.addObject("pageMode", "EDIT");

        String _id = StringUtils.defaultString(request.getParameter("_id"), "");

        Route route = routeService.getOnlyRouteInfo(_id);
        mav.addObject("route", route);

        return mav;
    }

    @RequestMapping("/busMng/editRoute")
    @ResponseBody
    public String editRoute(ServletRequest request) throws Exception {

        Route route = convertRouteByRequest(request);

        if(!route.isEditValidate()) {
            return "값이 올바르지 않습니다. 필요한 값을 모두 입력했는지 확인하세요.";
        }

        try {
            routeService.editRoute(route);
        } catch(Exception e) {
            e.printStackTrace();
            return "수정 중 에러가 발생 했습니다.";
        }

        return "수정 완료 되었습니다.";
    }

    @RequestMapping("/busMng/delRoute")
    @ResponseBody
    public String delRoute(ServletRequest request) throws Exception {

        Route route = convertRouteByRequest(request);

        if(!route.isDeleteValidate()) {
            return "값이 올바르지 않습니다.";
        }

        try {
            routeService.deleteRoute(route);
        } catch(Exception e) {
            e.printStackTrace();
            return "삭제 중 에러가 발생 했습니다.";
        }

        return "삭제 완료 되었습니다.";
    }

    @RequestMapping("/busMng/busMngInfo")
    public ModelAndView viewBusMngInfo(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/busMngInfo");

        String _id = (String) request.getParameter("_id");
        mav.addObject("route", getRouteInfo(_id));

        return mav;
    }

    private Route getRouteInfo(String _id) throws Exception {
		return routeService.getRouteInfo(_id);
	}

    @RequestMapping("/busMng/viewAddTripPlan")
    public ModelAndView viewAddTripPlan(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/tripPlanForm");
		mav.addObject("pageMode", "ADD");

		String _id = (String) request.getParameter("_id");
		Route route = getRouteInfo(_id);
        mav.addObject("route", route);

		TripPlan tripPlan = new TripPlan();
		tripPlan.setRouteId(route.getRouteId());
		tripPlan.setRouteName(route.getRouteName());
		mav.addObject("tripPlan", tripPlan);

        return mav;
    }

    @RequestMapping("/busMng/addTripPlan")
	@ResponseBody
	public String addTripPlan(ServletRequest request) throws Exception {

		TripPlan tripPlan = convertTripPlanByRequest(request);

    	if(!tripPlan.isAddValidate()) {
			return "값이 올바르지 않습니다. 필요한 값을 모두 입력했는지 확인하세요.";
		}

		try {
			tripPlanService.addTripPlan(tripPlan);
		} catch(Exception e) {
			e.printStackTrace();
			return "등록 중 에러가 발생 했습니다.";
		}

    	return "등록 완료 되었습니다.";
	}

	@RequestMapping("/busMng/viewEditTripPlan")
	public ModelAndView viewEditTripPlan(ServletRequest request) throws Exception {
    	ModelAndView mav = new ModelAndView();
		mav.setViewName("busMng/tripPlanForm");
		mav.addObject("pageMode", "EDIT");

        String _id = StringUtils.defaultString(request.getParameter("_id"), "");
		String tripPlanId = StringUtils.defaultString(request.getParameter("tripPlanId"), "");

        Route route = getRouteInfo(_id);
        mav.addObject("route", route);

		TripPlan tripPlan = tripPlanService.getTripPlan(tripPlanId);
		tripPlan.setRouteName(route.getRouteName());
		mav.addObject("tripPlan", tripPlan);

		return mav;
	}

	@RequestMapping("/busMng/editTripPlan")
	@ResponseBody
	public String editTripPlan(ServletRequest request) throws Exception {

		TripPlan tripPlan = convertTripPlanByRequest(request);

		if(!tripPlan.isEditValidate()) {
			return "값이 올바르지 않습니다. 필요한 값을 모두 입력했는지 확인하세요.";
		}

		try {
			tripPlanService.editTripPlan(tripPlan);
		} catch(Exception e) {
			e.printStackTrace();
			return "수정 중 에러가 발생 했습니다.";
		}

		return "수정 완료 되었습니다.";
	}

	@RequestMapping("/busMng/delTripPlan")
	@ResponseBody
	public String delTripPlan(ServletRequest request) throws Exception {

		TripPlan tripPlan = convertTripPlanByRequest(request);

		if(!tripPlan.isDeleteValidate()) {
			return "값이 올바르지 않습니다.";
		}

		try {
			tripPlanService.deleteTripPlan(tripPlan);
		} catch(Exception e) {
			e.printStackTrace();
			return "삭제 중 에러가 발생 했습니다.";
		}

		return "삭제 완료 되었습니다.";
	}

    private Route convertRouteByRequest(ServletRequest request) throws Exception {
        String _id = StringUtils.defaultString(request.getParameter("_id"), "");
        String routeId = StringUtils.defaultString(request.getParameter("routeId"), "");
        String routeName = StringUtils.defaultString(request.getParameter("routeName"), "");
        String weekdayCount = StringUtils.defaultString(request.getParameter("weekdayCount"), "0");
        String weekendCount = StringUtils.defaultString(request.getParameter("weekendCount"), "0");
        String weekdayDoubleDeckerCount = StringUtils.defaultString(request.getParameter("weekdayDoubleDeckerCount"), "0");
        String weekendDoubleDeckerCount = StringUtils.defaultString(request.getParameter("weekendDoubleDeckerCount"), "0");
        String companyName = StringUtils.defaultString(request.getParameter("companyName"), "");
        String peekAlloc = StringUtils.defaultString(request.getParameter("peekAlloc"), "");
        String nPeekAlloc = StringUtils.defaultString(request.getParameter("nPeekAlloc"), "");
        String startStationId = StringUtils.defaultString(request.getParameter("startStationId"), "");
        String startStationName = StringUtils.defaultString(request.getParameter("startStationName"), "");
        String upFirstTime = StringUtils.defaultString(request.getParameter("upFirstTime"), "");
        String upLastTime = StringUtils.defaultString(request.getParameter("upLastTime"), "");
        String endStationId = StringUtils.defaultString(request.getParameter("endStationId"), "");
        String endStationName = StringUtils.defaultString(request.getParameter("endStationName"), "");
        String downFirstTime = StringUtils.defaultString(request.getParameter("downFirstTime"), "");
        String downLastTime = StringUtils.defaultString(request.getParameter("downLastTime"), "");
        String dataGatherBatchEnabled = StringUtils.defaultString(request.getParameter("dataGatherBatchEnabled"), "N");
        String dataGatherBatchSchedule = StringUtils.defaultString(request.getParameter("dataGatherBatchSchedule"), "");

        Route route = new Route();
        if(!"".equals(_id)) {
            route = routeService.getOnlyRouteInfo(_id);
        }
        route.setRouteId(routeId);
        route.setRouteName(routeName);
        route.setCompanyName(companyName);
        route.setPeekAlloc(peekAlloc);
        route.setNPeekAlloc(nPeekAlloc);
        route.setStartStationId(startStationId);
        route.setStartStationName(startStationName);
        route.setUpFirstTime(upFirstTime);
        route.setUpLastTime(upLastTime);
        route.setEndStationId(endStationId);
        route.setEndStationName(endStationName);
        route.setDownFirstTime(downFirstTime);
        route.setDownLastTime(downLastTime);
        route.setWeekdayCount(Integer.valueOf(weekdayCount));
        route.setWeekendCount(Integer.valueOf(weekendCount));
        route.setWeekdayDoubleDeckerCount(Integer.valueOf(weekdayDoubleDeckerCount));
        route.setWeekendDoubleDeckerCount(Integer.valueOf(weekendDoubleDeckerCount));

        DataGatherScheduler dataGatherScheduler = route.getDataGatherScheduler();
        if (dataGatherScheduler == null) {
            dataGatherScheduler = new DataGatherScheduler();
        }
        dataGatherScheduler.setEnabled(("Y".equals(dataGatherBatchEnabled)) ? true : false);
        if(dataGatherScheduler.isEnabled()) {
            dataGatherScheduler.setSchedule(dataGatherBatchSchedule);
        } else {
            dataGatherScheduler.setSchedule("");
        }
        route.setDataGatherScheduler(dataGatherScheduler);

        return route;
    }

	private TripPlan convertTripPlanByRequest(ServletRequest request) throws Exception {
		String tripPlanId = StringUtils.defaultString(request.getParameter("tripPlanId"), "");
		int turnNumber = Integer.parseInt(StringUtils.defaultString(request.getParameter("turnNumber"), "0"));
		String routeId = StringUtils.defaultString(request.getParameter("routeId"), "");
		String plateNo = StringUtils.defaultString(request.getParameter("plateNo"), "");
		String plateType = StringUtils.defaultString(request.getParameter("plateType"), "");
		String weekendOperationYn = StringUtils.defaultString(request.getParameter("weekendOperationYn"), "N");
		String spareYn = StringUtils.defaultString(request.getParameter("spareYn"), "N");
		String schoolBreakReductionYn = StringUtils.defaultString(request.getParameter("schoolBreakReductionYn"), "N");
		String schoolBreakReductionStartedAt = StringUtils.defaultString(request.getParameter("schoolBreakReductionStartedAt"), "");
		String tripStopYn = StringUtils.defaultString(request.getParameter("tripStopYn"), "N");
		String tripStopStartedAt = StringUtils.defaultString(request.getParameter("tripStopStartedAt"), "");

		TripPlan tripPlan = new TripPlan();
		if (!"".equals(tripPlanId)) {
			tripPlan = tripPlanService.getTripPlan(tripPlanId);
		}
		tripPlan.setRouteId(routeId);
		tripPlan.setPlateNo(plateNo);
		tripPlan.setTurnNumber(turnNumber);
		tripPlan.setPlateType(plateType);
		tripPlan.setWeekendOperationYn(weekendOperationYn);
		tripPlan.setSpareYn(spareYn);
		tripPlan.setSchoolBreakReductionYn(schoolBreakReductionYn);
		if ("Y".equals(schoolBreakReductionYn) && !"".equals(schoolBreakReductionStartedAt)) {
			tripPlan.setSchoolBreakReductionStartedAt(getLocalDateTime(schoolBreakReductionStartedAt));
		}
		tripPlan.setTripStopYn(tripStopYn);
		if ("Y".equals(tripStopYn) && !"".equals(tripStopStartedAt)) {
			tripPlan.setTripStopStartedAt(getLocalDateTime(tripStopStartedAt));
		}

		return tripPlan;
	}

	private LocalDateTime getLocalDateTime(String dateStr) {
		dateStr = StringUtils.defaultString(dateStr, "");
		dateStr = dateStr.replace("-", "");
		dateStr = dateStr.replace("/", "");

		if (dateStr.length() < 8)
			return LocalDateTime.now();

		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6, 8);
		month = (month.indexOf("0") == 0) ? month.substring(1,2) : month;
		day = (day.indexOf("0") == 0) ? day.substring(1,2) : day;
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day), 0, 0, 0);
	}

}
