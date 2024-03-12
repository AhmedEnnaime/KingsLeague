import { createAsyncThunk } from "@reduxjs/toolkit";
import IStadium from "../../interfaces/IStadium";
import API from "../../utils/API";
import { AxiosResponse } from "axios";

export const fetchAllStadiums = createAsyncThunk<IStadium[]>(
  "stadium/all",
  async () => {
    const { data } = await API.get(`/MATCH-SERVICE/api/v1/stadiums`);
    return data;
  }
);

export const createStadium = createAsyncThunk<IStadium, Partial<IStadium>>(
  "stadium/create",
  async (stadium) => {
    const { data } = await API.post(`/MATCH-SERVICE/api/v1/stadiums`, stadium);
    return data;
  }
);

export const deleteStadium = createAsyncThunk<Map<string, string>, number>(
  "stadium/delete",
  async (id) => {
    const response: AxiosResponse = await API.delete(
      `/MATCH-SERVICE/api/v1/stadiums/${id}`
    );
    return response.data;
  }
);
