package com.ayurma.ayuromaweb.client.mobile;

import com.ayurma.ayuromaweb.client.mobile.place.AboutUsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.EnquiryMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.HomePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductDetailsMobilePlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductGroupPlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductPlace;
import com.ayurma.ayuromaweb.client.mobile.place.ProductsGroupsMenuPlace;
import com.google.gwt.place.shared.Place;
import com.googlecode.mgwt.mvp.client.AnimationMapper;
import com.googlecode.mgwt.ui.client.widget.animation.Animation;
import com.googlecode.mgwt.ui.client.widget.animation.Animations;


public class PhoneAnimationMapper implements AnimationMapper {
	@Override
	public Animation getAnimation(Place oldPlace, Place newPlace) {
		if (oldPlace instanceof ProductsGroupsMenuPlace && newPlace instanceof HomePlace) {
			return Animations.SLIDE_REVERSE;
		}
		if (oldPlace instanceof HomePlace && newPlace instanceof EnquiryMobilePlace) {

			return Animations.POP;
		}
		if (oldPlace instanceof EnquiryMobilePlace && newPlace instanceof HomePlace) {
			//return Animations.SLIDE_REVERSE;
			return Animations.POP_REVERSE;
		}
		if (oldPlace instanceof ProductGroupPlace && newPlace instanceof HomePlace) {
			//return Animations.SLIDE_REVERSE;
			return Animations.POP_REVERSE;
		}
		if (oldPlace instanceof ProductPlace && newPlace instanceof HomePlace) {
			//return Animations.SLIDE_REVERSE;
			return Animations.POP_REVERSE;
		}
		//about us view:
		if (oldPlace instanceof HomePlace && newPlace instanceof AboutUsMobilePlace) {

			return Animations.POP;
		}
		if (oldPlace instanceof AboutUsMobilePlace && newPlace instanceof HomePlace) {
			//return Animations.SLIDE_REVERSE;
			return Animations.POP_REVERSE;
		}
		if (oldPlace instanceof ProductGroupPlace && newPlace instanceof ProductsGroupsMenuPlace) {
			return Animations.SLIDE_REVERSE;
		}
		// going to enquiry place from product place:
		
		if (oldPlace instanceof ProductPlace && newPlace instanceof EnquiryMobilePlace) {

			return Animations.POP;
		}
		if (oldPlace instanceof EnquiryMobilePlace && newPlace instanceof ProductPlace) {

			return Animations.POP_REVERSE;
		}
		// going to product details place from product place:
		if (oldPlace instanceof ProductPlace && newPlace instanceof ProductDetailsMobilePlace) {

			return Animations.POP;
		}
		if (oldPlace instanceof ProductDetailsMobilePlace && newPlace instanceof ProductPlace) {

			return Animations.POP_REVERSE;
		}
		// going to home from productDetailsPlace
		if (oldPlace instanceof ProductDetailsMobilePlace && newPlace instanceof HomePlace) {

			return Animations.POP_REVERSE;
		}
		/*
		if (oldPlace instanceof UIPlace && newPlace instanceof HomePlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof AboutPlace && newPlace instanceof HomePlace) {
			return Animations.SLIDE_UP_REVERSE;
		}

		if (oldPlace instanceof HomePlace && newPlace instanceof AboutPlace) {
			return Animations.SLIDE_UP;
		}

		if (oldPlace instanceof HomePlace && newPlace instanceof AnimationPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof HomePlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof HomePlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ScrollWidgetPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof ScrollWidgetPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ElementsPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof ElementsPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof FormsPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof FormsPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ButtonBarPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof ButtonBarPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof SearchBoxPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof SearchBoxPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof TabBarPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof TabBarPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ButtonPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof ButtonPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof PopupPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof PopupPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ProgressBarPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof ProgressBarPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof ProgressIndicatorPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof ProgressIndicatorPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof SliderPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof SliderPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof PullToRefreshPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof PullToRefreshPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof CarouselPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof CarouselPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		if (oldPlace instanceof UIPlace && newPlace instanceof GroupedCellListPlace) {
			return Animations.SLIDE;
		}

		if (oldPlace instanceof GroupedCellListPlace && newPlace instanceof UIPlace) {
			return Animations.SLIDE_REVERSE;
		}

		// animation

		if (oldPlace instanceof AnimationSlidePlace && newPlace instanceof AnimationPlace) {
			return Animations.SLIDE_REVERSE;
		}

		// if (oldPlace instanceof AnimationCubePlace && newPlace instanceof
		// AnimationPlace) {
		// return Animation.CUBE_REVERSE;
		// }
		//
		// if (oldPlace instanceof AnimationPlace && newPlace instanceof
		// AnimationCubePlace) {
		// return Animation.CUBE;
		// }

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationSlideUpPlace) {
			return Animations.SLIDE_UP;
		}

		if (oldPlace instanceof AnimationSlideUpPlace && newPlace instanceof AnimationPlace) {
			return Animations.SLIDE_UP_REVERSE;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationDissolvePlace) {
			return Animations.DISSOLVE;
		}

		if (oldPlace instanceof AnimationDissolvePlace && newPlace instanceof AnimationPlace) {
			return Animations.DISSOLVE_REVERSE;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationFadePlace) {
			return Animations.FADE;
		}

		if (oldPlace instanceof AnimationFadePlace && newPlace instanceof AnimationPlace) {
			return Animations.FADE_REVERSE;
		}
		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationFlipPlace) {
			return Animations.FLIP;
		}

		if (oldPlace instanceof AnimationFlipPlace && newPlace instanceof AnimationPlace) {
			return Animations.FLIP_REVERSE;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationPopPlace) {
			return Animations.POP;
		}

		if (oldPlace instanceof AnimationPopPlace && newPlace instanceof AnimationPlace) {
			return Animations.POP_REVERSE;
		}

		if (oldPlace instanceof AnimationPlace && newPlace instanceof AnimationSwapPlace) {
			return Animations.SWAP;
		}

		if (oldPlace instanceof AnimationSwapPlace && newPlace instanceof AnimationPlace) {
			return Animations.SWAP_REVERSE;
		}
*/
		return Animations.SLIDE;

	}

}
