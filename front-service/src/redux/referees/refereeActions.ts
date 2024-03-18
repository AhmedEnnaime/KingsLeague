import { createAsyncThunk } from "@reduxjs/toolkit";
import API from "../../utils/API";
import { AxiosResponse } from "axios";
import IReferee from "../../interfaces/IReferee";

export const fetchAllReferees = createAsyncThunk<IReferee[]>(
  "referee/all",
  async () => {
    const { data } = await API.get(`/MATCH-SERVICE/api/v1/referees`);
    return data;
  }
);

export const createReferee = createAsyncThunk<IReferee, Partial<IReferee>>(
  "referee/create",
  async (referee) => {
    const { data } = await API.post(`/MATCH-SERVICE/api/v1/referees`, referee);
    return data;
  }
);

export const deleteReferee = createAsyncThunk<Map<string, string>, number>(
  "referee/delete",
  async (id) => {
    const response: AxiosResponse = await API.delete(
      `/MATCH-SERVICE/api/v1/referees/${id}`
    );
    return response.data;
  }
);

export const updateReferee = createAsyncThunk<
  IReferee,
  { id: number; refereeData: Partial<IReferee> }
>("referee/update", async ({ id, refereeData }) => {
  const { data } = await API.put(
    `/MATCH-SERVICE/api/v1/referees/${id}`,
    refereeData
  );
  return data;
});
