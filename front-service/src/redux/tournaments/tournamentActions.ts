import { createAsyncThunk } from "@reduxjs/toolkit";
import ITournament from "../../interfaces/ITournament";
import API from "../../utils/API";

export const fetchAllTournaments = createAsyncThunk<ITournament[]>(
  "tournament/all",
  async () => {
    const { data } = await API.get(`/TOURNAMENT-SERVICE/api/v1/tournaments`);
    return data;
  }
);
