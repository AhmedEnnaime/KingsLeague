import { createAsyncThunk } from "@reduxjs/toolkit";
import IRound from "../../interfaces/IRound";
import API from "../../utils/API";

export const fetchRoundsByTournamentId = createAsyncThunk<IRound[], number>(
  "round/all",
  async (id) => {
    const { data } = await API.get(
      `/ROUND-SERVICE/api/v1/matchDays/tournament/${id}`
    );
    return data;
  }
);
