import { createSlice } from "@reduxjs/toolkit";
import IStadium from "../../interfaces/IStadium";
import { fetchAllStadiums } from "./stadiumActions";

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
  extraReducers: (builder) => {
    builder.addCase(fetchAllStadiums.fulfilled, (state, action) => {
      state.stadiums = action.payload;
    });
  },
  reducers: {},
});

export default stadiumSlice.reducer;
