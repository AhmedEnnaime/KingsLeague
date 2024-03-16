import { createAsyncThunk } from "@reduxjs/toolkit";
import ITeam from "../../interfaces/ITeam";
import API from "../../utils/API";
import { AxiosResponse } from "axios";

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

export const createTeam = createAsyncThunk<ITeam, Partial<ITeam>>(
  "team/create",
  async (team) => {
    const { data } = await API.post(`/TEAM-SERVICE/api/v1/teams`, team);
    return data;
  }
);

export const deleteTeam = createAsyncThunk<Map<string, string>, number>(
  "team/delete",
  async (id) => {
    const response: AxiosResponse = await API.delete(
      `/TEAM-SERVICE/api/v1/teams/${id}`
    );
    return response.data;
  }
);

export const updateTeam = createAsyncThunk<
  ITeam,
  { id: number; teamData: Partial<ITeam> }
>("team/update", async ({ id, teamData }) => {
  const { data } = await API.put(`/TEAM-SERVICE/api/v1/teams/${id}`, teamData);
  return data;
});
