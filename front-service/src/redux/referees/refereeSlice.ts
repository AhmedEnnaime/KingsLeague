import { createSlice } from "@reduxjs/toolkit";
import IReferee from "../../interfaces/IReferee";
import {
  createReferee,
  deleteReferee,
  fetchAllReferees,
  updateReferee,
} from "./refereeActions";

interface RefereeState {
  referees: IReferee[];
  loading: boolean;
}

const initialState: RefereeState = {
  referees: [],
  loading: false,
};

const refereeSlice = createSlice({
  name: "referee",
  initialState,
  extraReducers: (builder) => {
    builder
      .addCase(fetchAllReferees.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchAllReferees.fulfilled, (state, action) => {
        state.loading = false;
        state.referees = action.payload;
      })
      .addCase(createReferee.pending, (state) => {
        state.loading = true;
      })
      .addCase(createReferee.fulfilled, (state, action) => {
        state.loading = false;
        state.referees.push(action.payload);
      })
      .addCase(deleteReferee.pending, (state) => {
        state.loading = true;
      })
      .addCase(deleteReferee.fulfilled, (state, action) => {
        state.loading = false;
        state.referees = state.referees.filter(
          (referee) => referee.id !== action.meta.arg
        );
      })
      .addCase(updateReferee.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateReferee.fulfilled, (state, action) => {
        state.loading = false;
        const updatedRefereeId = action.meta.arg.id;
        const updatedReferee = action.payload;
        const existingReferee = state.referees.find(
          (referee) => referee.id === updatedRefereeId
        );
        if (existingReferee) {
          Object.assign(existingReferee, updatedReferee);
        }
      });
  },
  reducers: {},
});

export default refereeSlice.reducer;
