import { createAsyncThunk } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import API from "../../utils/API";

export const fetchAllTeams = createAsyncThunk<ITeam[]>("team/all", async () => {
  const { data } = await API.get(`/TEAM-SERVICE/api/v1/teams`);
  return data;
});

export const fetchSelectedTeam = createAsyncThunk<ITeam, number>(
  "team/single",
  async (id) => {
    const { data } = await API.get(`/TEAM-SERVICE/api/v1/teams/${id}`);
    return data;
  }
);
