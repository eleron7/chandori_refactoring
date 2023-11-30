import { createStore, combineReducers } from "redux";
import sessionReducer from "./sessionReducer";

const rootReducer = combineReducers({sessionReducer});
const store = createStore(rootReducer);

export default store;