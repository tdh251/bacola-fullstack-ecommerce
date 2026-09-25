import { UserIcon } from "@heroicons/react/24/outline";

function PersonalAccount() {
   return (
      <div className="relative">
         <button className="flex-center w-[42px] h-[42px] bg-white border border-[#e2e4ec] rounded-full">
            <UserIcon className="w-[18px]" />
         </button>
      </div>
   );
}

export default PersonalAccount;
