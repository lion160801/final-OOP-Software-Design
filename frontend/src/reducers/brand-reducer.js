import {FETCH_BRANDS
} from "../utils/constants/actions-types";

const initialState = {
    brands: [],
};

const reducer = (state = initialState, action) => {
    const {type, payload} = action

    switch (type) {
        case FETCH_BRANDS:
            return {...state, brands: payload};
        default:
            return state;
    }
};

export default reducer;