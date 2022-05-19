import axios from 'axios';

import {
    FETCH_PERFUMES,
    FETCH_PERFUME,
    FETCH_PERFUMES_BY_GENDER,
    FETCH_PERFUMES_BY_PERFUMER,
    FETCH_PERFUMES_BY_FILTER_PARAMS, FETCH_BRANDS
} from "../utils/constants/actions-types";
import {API_BASE_URL} from "../utils/constants/url";

export const fetchPerfumes = () => async (dispatch) => {
    const response = await axios.get(API_BASE_URL);

    dispatch({
        type: FETCH_PERFUMES,
        payload: response.data
    })
};

export const fetchPerfume = (id) => async (dispatch) => {
    const response = await axios.get(API_BASE_URL + "/product/" + id);

    dispatch({
        type: FETCH_PERFUME,
        payload: response.data
    })
};

export const fetchBrands = () => async (dispatch) => {
    const response = await axios.get(API_BASE_URL + "/brands");

    dispatch({
        type: FETCH_BRANDS,
        payload: response.data
    })
};

export const fetchPerfumesByGender = (gender) => async (dispatch) => {
    const response = await axios.post(API_BASE_URL + "/menu/gender", gender);

    dispatch({
        type: FETCH_PERFUMES_BY_GENDER,
        payload: response.data
    })
};

export const fetchPerfumesByPerfumer = (brand) => async (dispatch) => {
    const response = await axios.post(API_BASE_URL + "/menu/brand", brand);

    dispatch({
        type: FETCH_PERFUMES_BY_PERFUMER,
        payload: response.data
    })
};

export const fetchPerfumesByFilterParams = (filter) => async (dispatch) => {
    const response = await axios.post(API_BASE_URL + "/menu/search", filter);

    dispatch({
        type: FETCH_PERFUMES_BY_FILTER_PARAMS,
        payload: response.data
    })
};

