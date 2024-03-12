import { createSlice } from "@reduxjs/toolkit";
import IStadium from "../../interfaces/IStadium";

interface StadiumState {
  stadiums: IStadium[];
  selectedStadiumID: number | null;
  loading: boolean;
}

const initialState: StadiumState = {
  stadiums: [],
  selectedStadiumID: null,
  loading: false,
};

const stadiumSlice = createSlice({
  name: "stadium",
  initialState,
  reducers: {},
});

export default stadiumSlice.reducer;
