
export default function FilterSkeleton() {
    return <div role="status" className="w-96 p-4 border border-gray-100 rounded shadow animate-pulse md:p-6 dark:border-gray-100">
        <div className="flex items-center justify-center h-48 mb-4 bg-gray-300 rounded dark:bg-gray-200">

        </div>
        <div className="h-2.5 bg-gray-200 rounded-full dark:bg-gray-200 w-48 mb-4"></div>
        <div className="h-2 bg-gray-200 rounded-full dark:bg-gray-200 mb-2.5"></div>
        <div className="h-2 bg-gray-200 rounded-full dark:bg-gray-200 mb-2.5"></div>
        <div className="h-2 bg-gray-200 rounded-full dark:bg-gray-200"></div>
        <div className="flex items-center mt-4">
    
            <div>
                <div className="h-2.5 bg-gray-200 rounded-full dark:bg-gray-200 w-32 mb-2"></div>
                <div className="w-48 h-2 bg-gray-200 rounded-full dark:bg-gray-200"></div>
            </div>
        </div>
        <span className="sr-only">Loading...</span>
    </div>    
    };
    