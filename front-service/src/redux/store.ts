import { configureStore } from "@reduxjs/toolkit";
import stadiumReducer from "./stadiums/stadiumSlice";
import { useDispatch } from "react-redux";

export const store = configureStore({
  reducer: {
    stadium: stadiumReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export const useAppDispatch = () => useDispatch<AppDispatch>();