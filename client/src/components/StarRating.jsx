import { StarIcon } from "@heroicons/react/24/outline";
import { StarIcon as StarSolid } from "@heroicons/react/24/solid";
function StarRating({ rating, size = "w-4 h-4" }) {
   return (
      <div className="flex text-amber-400 gap-0.5">
         {[1, 2, 3, 4, 5].map((star) =>
            star <= rating ? (
               <StarSolid key={star} className={size} />
            ) : (
               <StarIcon key={star} className={`${size} text-gray-300`} />
            ),
         )}
      </div>
   );
}

export default StarRating;
