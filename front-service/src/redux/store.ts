import { configureStore } from "@reduxjs/toolkit";
import stadiumReducer from "./stadiums/stadiumSlice";
import tournamentReducer from "./tournaments/tournamentSlice";
import matchDayReducer from "./matchDays/matchDaySlice";
import roundReducer from "./rounds/roundSlice";
import { useDispatch } from "react-redux";

export const store = configureStore({
  reducer: {
    stadium: stadiumReducer,
    tournament: tournamentReducer,
    matchDay: matchDayReducer,
    round: roundReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export const useAppDispatch = () => useDispatch<AppDispatch>();
