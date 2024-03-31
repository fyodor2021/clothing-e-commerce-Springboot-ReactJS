import {useState} from 'react'
import { GoChevronDown, GoChevronUp } from 'react-icons/go'
import arzBrand from '../statics/arz-brand.png'

export default function Accordion({items}) {
    const [expandedIndex, setExpandedIndex]  = useState(null)
    const handleClick = (index) => {
        setExpandedIndex((currentExpandedIndex) => {
            if(currentExpandedIndex === index){
                return null;
            }else{
                return index;
            }
        });
    }
    const renderedItems = items.map((item, index) => {
        const expanded = index === expandedIndex
        const icon = <span className='text-2xl'>
           { expanded ? <GoChevronDown/> : <GoChevronUp/>}
        </span>
        return <div key={item.id}>
            <div className='flex justify-between p-3 bg-gray-50 border-b items-center cursor-pointer' 
            onClick={() => handleClick(index)}>{item.label}{icon}</div>
            {expanded && <div className='border-b p-5 '>
            <div className="arz-card">
        <div className="arz-card-number">4242 4242 4242 4242</div>
        <div>
            <div className="arz-card-holder-name">Vedoor Barakat</div>
            <img src={arzBrand} width="80" />
        </div>
    </div>
                
                
                
                
                </div>}
        </div>
    })
    return <div className='border-x border-t rounded'>{renderedItems}</div>
}













































// import {useState} from 'react'
// export default function Accordion({items}) {
//     const [expandedIndex, setExpandedIndex] = useState(0)
//     const renderedItems = items.map((item, index) => {
//         const isExpanded = index === expandedIndex;
        
//             return (
//                 <div key={item.id}>
//                     <div onClick={() => setExpandedIndex(index)}>{item.label}</div>
//                     {isExpanded && <div>{item.content}</div>}
//                 </div>
//             )
//     })
//     return <div>{renderedItems}</div>
// }