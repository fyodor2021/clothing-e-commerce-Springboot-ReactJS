import { Link} from "react-router-dom"
export default function ErrorPage() {
    return (
<section className="flex items-center h-screen w-screen p-16 justify-center">
	<div className="container flex flex-col items-center justify-center px-5 mx-auto my-8">
		<div className="max-w-md text-center">
			<h2 className="font-extrabold dark:text-gray-400 font-size-12rem">
				<span className="sr-only">Error</span>404
			</h2>
			<p className="text-2xl font-Roboto md:text-3xl">Sorry, we couldn't find this page.</p>
			<p className="mt-4 mb-8 dark:text-gray-600">But dont worry, you can find plenty of other things on our homepage.</p>
			<Link rel="noopener noreferrer" to={'/'} className="px-8 py-3 rounded  button">Back to homepage</Link>
		</div>
	</div>
</section>)
}