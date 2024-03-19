import { createAsyncThunk } from "@reduxjs/toolkit";
import IRound from "../../interfaces/IRound";
import API from "../../utils/API";

export const fetchRoundsByTournamentId = createAsyncThunk<IRound[], number>(
  "round/all",
  async (id) => {
    const { data } = await API.get(
      `/ROUND-SERVICE/api/v1/rounds/tournament/${id}`
    );
    return data;
  }
);

export const createRound = createAsyncThunk<IRound, Partial<IRound>>(
  "round/create",
  async (round) => {
    const { data } = await API.post(`/ROUND-SERVICE/api/v1/rounds`, round);
    return data;
  }
);
