package com.dazzilove.bustrace.controller.web;

import com.dazzilove.bustrace.domain.Bus;
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

		List<Bus> busList = routeService.getRoutes();
        mav.addObject("busList", busList);

        return mav;
    }

    @RequestMapping("/busMng/busMngInfo")
    public ModelAndView viewBusMngInfo(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/busMngInfo");

        String routeId = (String) request.getParameter("routeId");
        mav.addObject("route", getRouteInfo(routeId));

        return mav;
    }

    private Route getRouteInfo(String routeId) throws Exception {
		BusRouteInfo busRouteInfo = busRouteService.getBusRouteInfoItem(routeId);

		Route route = routeService.getRouteInfo(routeId);
		if(busRouteInfo != null) {
			route.setCompanyId(busRouteInfo.getCompanyId());
			route.setCompanyName(busRouteInfo.getCompanyName());
			route.setCompanyTel(busRouteInfo.getCompanyTel());
			route.setDistrictCd(busRouteInfo.getDistrictCd());
			route.setDownFirstTime(busRouteInfo.getDownFirstTime());
			route.setDownLastTime(busRouteInfo.getDownLastTime());
			route.setEndMobileNo(busRouteInfo.getEndMobileNo());
			route.setEndStationId(busRouteInfo.getEndStationId());
			route.setEndStationName(busRouteInfo.getEndStationName());
			route.setPeekAlloc(busRouteInfo.getPeekAlloc());
			route.setRegionName(busRouteInfo.getRegionName());
			route.setRouteId(busRouteInfo.getRouteId());
			route.setRouteName(busRouteInfo.getRouteName());
			route.setRouteTypeCd(busRouteInfo.getRouteTypeCd());
			route.setRouteTypeName(busRouteInfo.getRouteTypeName());
			route.setStartMobileNo(busRouteInfo.getStartMobileNo());
			route.setStartStationId(busRouteInfo.getStartStationId());
			route.setStartStationName(busRouteInfo.getStartStationName());
			route.setUpFirstTime(busRouteInfo.getUpFirstTime());
			route.setUpLastTime(busRouteInfo.getUpLastTime());
			route.setNPeekAlloc(busRouteInfo.getNPeekAlloc());
		}

		return route;
	}

    @RequestMapping("/busMng/viewAddTripPlan")
    public ModelAndView viewAddTripPlan(ServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("busMng/tripPlanForm");
		mav.addObject("pageMode", "ADD");

		String routeId = (String) request.getParameter("routeId");
		Route route = getRouteInfo(routeId);

		TripPlan tripPlan = new TripPlan();
		tripPlan.setRouteId(routeId);
		tripPlan.setRouteName(route.getRouteName());
		mav.addObject("tripPlan", tripPlan);

        return mav;
    }

    @RequestMapping("/busMng/addTripPlanProc")
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

		String tripPlanId = StringUtils.defaultString(request.getParameter("tripPlanId"), "");

		TripPlan tripPlan = tripPlanService.getTripPlan(tripPlanId);
		Route route = getRouteInfo(tripPlan.getRouteId());

		tripPlan.setRouteName(route.getRouteName());
		mav.addObject("tripPlan", tripPlan);

		return mav;
	}

	@RequestMapping("/busMng/editTripPlanProc")
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

	private TripPlan convertTripPlanByRequest(ServletRequest request) throws Exception {
		String tripPlanId = StringUtils.defaultString(request.getParameter("tripPlanId"), "");
		String routeId = StringUtils.defaultString(request.getParameter("routeId"), "");
		String plateNo = StringUtils.defaultString(request.getParameter("plateNo"), "");
		String plateType = StringUtils.defaultString(request.getParameter("plateType"), "");
		String weekendOperationYn = StringUtils.defaultString(request.getParameter("weekendOperationYn"), "N");
		String spareYn = StringUtils.defaultString(request.getParameter("spareYn"), "N");
		String schoolBreakReductionYn = StringUtils.defaultString(request.getParameter("schoolBreakReductionYn"), "N");
		String schoolBreakReductionStartAt = StringUtils.defaultString(request.getParameter("schoolBreakReductionStartAt"), "");
		String tripStopYn = StringUtils.defaultString(request.getParameter("tripStopYn"), "N");
		String tripStopStartAt = StringUtils.defaultString(request.getParameter("tripStopStartAt"), "");

		TripPlan tripPlan = new TripPlan();
		if (!"".equals(tripPlanId)) {
			tripPlan = tripPlanService.getTripPlan(tripPlanId);
		}
		tripPlan.setRouteId(routeId);
		tripPlan.setPlateNo(plateNo);
		tripPlan.setPlateType(plateType);
		tripPlan.setWeekendOperationYn(weekendOperationYn);
		tripPlan.setSpareYn(spareYn);
		tripPlan.setSchoolBreakReductionYn(schoolBreakReductionYn);
		if ("Y".equals(schoolBreakReductionYn) && !"".equals(schoolBreakReductionStartAt)) {
			tripPlan.setSchoolBreakReductionStartAt(getLocalDateTime(schoolBreakReductionStartAt));
		}
		tripPlan.setTripStopYn(tripStopYn);
		if ("Y".equals(tripStopYn) && !"".equals(tripStopStartAt)) {
			tripPlan.setTripStopStartAt(getLocalDateTime(tripStopStartAt));
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
