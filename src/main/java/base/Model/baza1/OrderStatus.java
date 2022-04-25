package base.Model.baza1;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus implements IStateTransformation<OrderStatus> {
	
	
	PENDING(){
		@Override
		public  boolean transform(OrderStatus newState) {
			switch (newState){
			case PROCESSED:
			break;
			case CANCELLED:
			break;
			default:
				if (newState!=null&& this.equals(newState)) return true;
				//if (newState!=null&& entity.getState().equals(newState)) return entity;
				throw new IllegalArgumentException("Not proper state transition."+this.name()+" -> "+newState);	
			}
			return true;
			//return entity;
		}
	},
			
	PROCESSED(){

		@Override
		public boolean transform( OrderStatus newState) {
			switch (newState){
			case REJECTED:
			break;
			case ACCEPTED:
			break;
			case CANCELLED:
			break;
			default:
				if (newState!=null&& this.equals(newState)) return true;
				throw new IllegalArgumentException("Not proper state transition."+this.name()+" -> "+newState);	
			}
			return true;
			
		}
		
	},
	REJECTED(){

		@Override
		public boolean transform( OrderStatus newState) {
				throw new IllegalArgumentException("Not proper state transition."+this.name()+" -> "+newState);
		}
		
	},
	ACCEPTED(){
		@Override
		public boolean transform( OrderStatus newState) {
			switch (newState){
			case PROCESSED:
			break;
			case PENDING:
			break;
			case CANCELLED:
			break;
			default:
				throw new IllegalArgumentException("Not proper state transition."+this.name()+" -> "+newState);	
			}
			return true;
		}
		
	},
	CANCELLED(){

		@Override
		public boolean transform( OrderStatus newState) {
			switch (newState){
			case PROCESSED:
			break;
			case PENDING:
			break;
			default:
				if (newState!=null&& this.equals(newState)) return true;
				throw new IllegalArgumentException("Not proper state transition."+this.name()+" -> "+newState);	
			}
			return true;
		}
		
	};
	
	private List<OrderStatus> status;
	

	static{
			PENDING.status = Arrays.asList(
						new OrderStatus[]{
							OrderStatus.PROCESSED,
							OrderStatus.CANCELLED
						}
					);
			PROCESSED.status = Arrays.asList(
						new OrderStatus[]{
							OrderStatus.REJECTED,
							OrderStatus.ACCEPTED,
							OrderStatus.CANCELLED
						}
					);
			REJECTED.status = Arrays.asList(
						new OrderStatus[]{
							OrderStatus.PROCESSED,
							OrderStatus.PENDING,
							OrderStatus.CANCELLED
						}
					);
			ACCEPTED.status = Arrays.asList(
						new OrderStatus[]{
							OrderStatus.PROCESSED,
							OrderStatus.PENDING,
							OrderStatus.CANCELLED
						}
					);
			CANCELLED.status = Arrays.asList(
						new OrderStatus[]{
							OrderStatus.PROCESSED,
							OrderStatus.PENDING
						}
					);
	}

	
	
}

