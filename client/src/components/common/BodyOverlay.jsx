import useUIContext from "../../context/UIContext";

function BodyOverlay() {
   // const { isOpen, hanleClose } = useUIContext();
   return (
      <div
         className={"fixed inset-0 z-40 bg-black opacity-40 "}
         // onClick={hanleClose}
      ></div>
   );
}

export default BodyOverlay;
