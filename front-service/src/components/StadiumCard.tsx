import { useState } from "react";
import { StadiumCardProps } from "../propsTypes/StadiumCardProps";
import StadiumModal from "./StadiumModal";
import DeleteModal from "../shared/DeleteModal";

const StadiumCard = ({ stadium }: StadiumCardProps) => {
  const [openUpdate, setOpenUpdate] = useState(false);
  const [openDelete, setOpenDelete] = useState(false);

  return (
    <div className="flex flex-col items-center justify-center w-full max-w-sm mx-auto p-6">
      <div
        className="w-full h-64 bg-gray-300 bg-center bg-cover rounded-lg shadow-md"
        style={{
          backgroundImage: "url('../assets/stadium.jpeg')",
        }}
      ></div>
      <div className="w-56 -mt-10 overflow-hidden bg-white rounded-lg shadow-lg md:w-64 dark:bg-gray-800">
        <h3 className="py-2 font-bold tracking-wide text-center text-gray-800 uppercase dark:text-white">
          {stadium.name} ({stadium.capacity})
        </h3>

        <div className="flex items-center justify-between px-3 py-2 bg-gray-200 dark:bg-gray-700">
          <span className="font-bold text-gray-800 dark:text-gray-200">
            {stadium.location}
          </span>

          <button
            onClick={() => setOpenUpdate(true)}
            className="px-2 py-1 text-xs font-semibold text-white uppercase transition-colors duration-300 transform bg-blue-800 rounded hover:bg-blue-700 focus:outline-none"
          >
            Update
          </button>
          <button
            onClick={() => setOpenDelete(true)}
            className="px-2 py-1 text-xs font-semibold text-white uppercase transition-colors duration-300 transform bg-red-800 rounded hover:bg-red-700 focus:outline-none"
          >
            Delete
          </button>
        </div>
      </div>
      {openUpdate ? (
        <StadiumModal
          open={openUpdate}
          setOpen={setOpenUpdate}
          stadium={stadium}
        />
      ) : (
        ""
      )}
      {openDelete ? (
        <DeleteModal
          open={openDelete}
          setOpen={setOpenDelete}
          element={stadium}
        />
      ) : (
        ""
      )}
    </div>
  );
};

export default StadiumCard;
